package com.example.SwipeRest.controller.auth;

import com.example.SwipeRest.token.TokenRepo;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authenticate")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final TokenRepo tokenRepo;
//    @GetMapping("/login")
//    public  String logPage(Model model){
//        return "admin/login";
//    }
    @Hidden
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @Operation(summary = "Authenticate")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }
//    @PostMapping("/login")
//    public String login(@RequestParam String login, @RequestParam String password, Model model){
//        AuthenticationRequest request = new AuthenticationRequest();
//        request.setLogin(login);
//        request.setPassword(password);
//        authenticationService.authenticate(request);
//
//        String token = tokenRepo.findAllValidTokensByAdmin(18).get(0).getToken();
//        System.out.println(token);
//        model.addAttribute("token", token);
//        return "admin/admin_main";
//    }
    @Operation(summary = "Logout")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.ok("Success logout");
    }

    @Operation(summary = "Refresh Token")
    @PostMapping("/refresh-token")
    public ResponseEntity refresh(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        return authenticationService.refreshToken(request, response);
    }
}
