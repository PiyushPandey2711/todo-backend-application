package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class TagRequest {
	
	@NotBlank(message = "Tag name is required")
	private String name;

	public TagRequest() {
	}

	public String getName() {
	   return name;
	}

	public void setName(String name) {
	   this.name = name;
	}

}
