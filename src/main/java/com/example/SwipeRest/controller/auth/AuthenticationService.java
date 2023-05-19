package com.example.SwipeRest.controller.auth;

import com.example.SwipeRest.config.JwtService;
import com.example.SwipeRest.controller.auth.AuthenticationResponse;
import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.Role;
import com.example.SwipeRest.repository.UserRepo;
import com.example.SwipeRest.token.Token;
import com.example.SwipeRest.token.TokenRepo;
import com.example.SwipeRest.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo adminRepo;
    private final TokenRepo tokenRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var admin = User.builder().mail(request.getLogin()).password(passwordEncoder.encode(request.getPassword())).role(Role.ADMIN).blackList(false).build();
        var savedAdmin = adminRepo.save(admin);

        var jwtToken = jwtService.generateToken(admin);
        var refreshToken = jwtService.generateRefreshToken(admin);
        saveAdminToken(savedAdmin, jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    private void saveAdminToken(User admin, String jwtToken) {
        var token = Token.builder()
                .user(admin)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepo.save(token);
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        var admin = adminRepo.findByMail(request.getLogin()).orElseThrow();
        var jwtToken = jwtService.generateToken(admin);
        var refreshToken = jwtService.generateRefreshToken(admin);
        revokeAllAdminTokens(admin);
        saveAdminToken(admin,jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    private void revokeAllAdminTokens(User admin){
        var validAdminToken = tokenRepo.findAllValidTokensByAdmin(admin.getIdUser());
        if(validAdminToken.isEmpty()){
            return;
        }
        validAdminToken.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepo.saveAll(validAdminToken);
    }


    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String adminLogin;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        adminLogin = jwtService.extractUsername(refreshToken);
        if(adminLogin!=null ){
            var user = this.adminRepo.findByMail(adminLogin).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user) ){
                var accessToken = jwtService.generateToken(user);
                revokeAllAdminTokens(user);
                saveAdminToken(user,accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
            }
        }
    }
}
