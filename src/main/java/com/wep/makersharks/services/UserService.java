package com.wep.makersharks.services;


import com.wep.makersharks.DTO.UserSessionDTO;
import com.wep.makersharks.models.User;
import com.wep.makersharks.repository.UserRepository;
import jakarta.transaction.Transactional;
import  com.wep.makersharks.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Transactional
    public UserSessionDTO register(UserSessionDTO request) {
        try {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user = userRepository.save(user);
            String token = jwtService.generateToken(user);

            UserSessionDTO userSessionDTO = UserSessionDTO.builder().userId(user.getId()).token(token).email(user.getEmail()).username(user.getUsername())
                            .message("Registered Successfully !! Please Login !!").build();

            return userSessionDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new UserSessionDTO("Error Registering !!");
        }
    }


    @Transactional
    public UserSessionDTO login(UserSessionDTO request) {
        try {
            System.out.println(request.getEmail());
            User user = userRepository.findByEmail(request.getEmail());

            if(user == null ){
                throw new RuntimeException("No User found!!");
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            String token = jwtService.generateToken(user);

            UserSessionDTO userSessionDTO = UserSessionDTO.builder()
                    .email(user.getEmail())
                    .message("Login successful")
                    .token(token)
                    .build();

            return userSessionDTO;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Transactional
    public Boolean UserExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

}
