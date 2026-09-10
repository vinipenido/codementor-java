package com.vinicius.codementor.controller;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentIngestionController {

    private final EmbeddingStoreIngestor ingestor;

    public DocumentIngestionController(EmbeddingStoreIngestor ingestor) {
        this.ingestor = ingestor;
    }

    @GetMapping("/api/ingest")
    public String ingestTest(@RequestParam String filePath) {
        Document document = FileSystemDocumentLoader.loadDocument(filePath, new ApachePdfBoxDocumentParser());
        ingestor.ingest(document);
        return "Documento ingerido com sucesso!";
    }
}
