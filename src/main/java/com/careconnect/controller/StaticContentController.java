package com.careconnect.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/static")
public class StaticContentController {
    @GetMapping(value = "/{filename}", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getStaticPage(@PathVariable String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/" + filename);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        String content = Files.readString(resource.getFile().toPath());
        return ResponseEntity.ok(content);
    }
}
