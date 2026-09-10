package com.vinicius.codementor.controller;

import com.vinicius.codementor.model.Usuario;
import com.vinicius.codementor.repository.UsuarioRepository;
import com.vinicius.codementor.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/api/auth/registrar")
    public String registrar(@RequestParam String email, @RequestParam String senha) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuarioRepository.save(usuario);
        return "Usuário registrado com sucesso!";
    }

    @PostMapping("/api/auth/login")
    public String login(@RequestParam String email, @RequestParam String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.gerarToken(usuario.getEmail());
    }
}