package com.example.backend.service;

import com.example.backend.dto.TagRequest;
import com.example.backend.dto.TagResponse;

public interface TagService {
	TagResponse createTag(TagRequest request);

}
