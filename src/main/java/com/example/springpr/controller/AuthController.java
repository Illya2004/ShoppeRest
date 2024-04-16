package com.example.springpr.controller;

import com.example.springpr.dto.security.AuthResponseDTO;
import com.example.springpr.dto.security.LoginDTO;
import com.example.springpr.dto.security.RegisterDTO;
import com.example.springpr.entity.security.Role;
import com.example.springpr.entity.security.User;
import com.example.springpr.enums.RolesEnum;
import com.example.springpr.repository.security.RolesRepository;
import com.example.springpr.repository.security.UserRepository;
import com.example.springpr.security.JwtGenerator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/auth/")
@AllArgsConstructor
public class AuthController {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    private RolesRepository rolesRepository;
    private JwtGenerator jwtGenerator;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if(userRepository.existsByLogin(registerDTO.getLogin())){
            return new ResponseEntity<>("User is exists", HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .login(registerDTO.getLogin())
                .password(passwordEncoder.encode(registerDTO.getPassword())).build();

        Role roles = rolesRepository.findByName("USER");
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);

    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        User user = userRepository.findByLogin(loginDTO.getLogin()).get();
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword()).build();

        if (!passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

}
