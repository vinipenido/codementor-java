package com.vinicius.codementor.controller;

import com.vinicius.codementor.service.ProfessorPrograma;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ProfessorController {

    private final ProfessorPrograma professor;

    public ProfessorController(ProfessorPrograma professor) {
        this.professor = professor;
    }

    @GetMapping("/api/professor")
    public String perguntar(Principal principal, @RequestParam String pergunta) {
        String alunoId = principal.getName();
        return professor.responder(alunoId, pergunta);
    }
}
