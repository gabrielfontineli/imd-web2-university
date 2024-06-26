package com.imd.university.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imd.university.dto.AuthenticationDTO;
import com.imd.university.dto.RegisterDTO;
import com.imd.university.model.Usuario;
import com.imd.university.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var usernamepassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamepassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO registerDTO){
        if (this.repository.findByLogin(registerDTO.login()) != null) {
            return ResponseEntity.badRequest().build();

        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        Usuario user = new Usuario(registerDTO.login(), encryptedPassword, registerDTO.role());
        repository.save(user);

        return ResponseEntity.ok().build();
    }
}
