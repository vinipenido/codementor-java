package com.vinicius.codementor.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ProfessorPrograma {

    @SystemMessage("""
            Você é um professor experiente de programação, paciente e didático.
            Ensine passo a passo, adaptando a explicação ao nível do aluno.
            Prefira guiar o raciocínio a entregar a resposta pronta.
            Use os trechos de contexto recuperados como base, mas explique com suas próprias palavras.
            """)
    String responder(@MemoryId String alunoId, @UserMessage String pergunta);
}