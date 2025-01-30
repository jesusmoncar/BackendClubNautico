package com.clubnautico.clubnautico.Service;

import com.clubnautico.clubnautico.Config.JwtService;
import com.clubnautico.clubnautico.controller.Models.AuthResponse;
import com.clubnautico.clubnautico.controller.Models.AuthenticateRequest;
import com.clubnautico.clubnautico.controller.Models.RegisterRequest;

import com.clubnautico.clubnautico.entity.Role;
import com.clubnautico.clubnautico.entity.User;
import com.clubnautico.clubnautico.exception.NotFound;
import com.clubnautico.clubnautico.repository.UserRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AauthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        var existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new NotFound("El usuario ya existe");
        }

      var user = User.builder()
              .name(request.getName())
              .lastname(request.getLastname())
              .username(request.getUsername())
              .password(passwordEncoder.encode(request.getPassword()))
              .role(request.getRole())
              .build();
        userRepository.save(user);

              var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().
                    token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
