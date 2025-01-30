package com.clubnautico.clubnautico.Service;

import com.clubnautico.clubnautico.controller.Models.UserResponse;
import com.clubnautico.clubnautico.entity.User;
import com.clubnautico.clubnautico.exception.NotFound;
import com.clubnautico.clubnautico.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse getAuthenticatedUser() {
        // Obtener usuario autenticado
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new NotFound("Usuario no encontrado"));

        return new UserResponse(
                user.getName(),
                user.getLastname(),
                user.getUsername(),
                user.getRole()
        );
    }


    @Transactional
    public UserResponse updateAuthenticatedUser(UserResponse updatedUser) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new NotFound("Usuario no encontrado"));
        user.setName(updatedUser.getName());
        user.setLastname(updatedUser.getLastname());
        user.setUsername(updatedUser.getUsername());
        user.setRole(updatedUser.getRole());

        userRepository.save(user);
        return new UserResponse(user.getName(), user.getLastname(), user.getUsername(), user.getRole());
    }

}
