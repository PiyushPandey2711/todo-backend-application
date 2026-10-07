package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.TagRequest;
import com.example.backend.dto.TagResponse;
import com.example.backend.repository.TagRepository;
import com.example.backend.entity.Tag;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public TagResponse createTag(TagRequest request) {

        Tag tag = new Tag();

        tag.setName(request.getName());

        Tag savedTag = tagRepository.save(tag);

        return new TagResponse(
                savedTag.getId(),
                savedTag.getName()
        );
    }
}
