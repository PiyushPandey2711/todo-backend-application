package com.example.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.TagRequest;
import com.example.backend.dto.TagResponse;
import com.example.backend.service.TagService;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagResponse createTag(
            @Valid @RequestBody TagRequest request) {

        return tagService.createTag(request);
    }
}