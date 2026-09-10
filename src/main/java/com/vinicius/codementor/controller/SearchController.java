package com.vinicius.codementor.controller;

import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    private final ContentRetriever contentRetriever;

    public SearchController(ContentRetriever contentRetriever) {
        this.contentRetriever = contentRetriever;
    }

    @GetMapping("/api/search")
    public String searchTest(@RequestParam String query) {
        List<Content> results = contentRetriever.retrieve(Query.from(query));

        return results.stream()
                .map(content -> content.textSegment().text())
                .collect(Collectors.joining("\n\n---\n\n"));
    }
}
