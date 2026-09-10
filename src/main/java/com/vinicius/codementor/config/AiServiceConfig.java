package com.vinicius.codementor.config;

import com.vinicius.codementor.service.ProfessorPrograma;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.rag.RetrievalAugmentor;

@Configuration
public class AiServiceConfig {

    @Bean
    public ProfessorPrograma professorPrograma(
            ChatModel chatModel,
            RetrievalAugmentor retrievalAugmentor) {
        return AiServices.builder(ProfessorPrograma.class)
                .chatModel(chatModel)
                .retrievalAugmentor(retrievalAugmentor)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(20))
                .build();
    }
}
