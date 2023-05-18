package com.example.SwipeRest.service.impl;

//
//import com.example.SwipeRest.entity.User;
//import com.example.SwipeRest.repository.UserRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserRepo userRepo;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepo.findByMail(username);
//
//        if (user.isPresent()){
//            System.out.println(user.get().getRole());
//
//            User authAdmin = User.builder().mail(user.get().getMail()).password(user.get().getPassword()).role(user.get().getRole()).build();
//            return authAdmin;
//        }
//        else {
//            throw new UsernameNotFoundException("Invalid login or pass");
//        }
//
//    }
//}
