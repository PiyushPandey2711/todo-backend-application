package com.example.backend.dto;

import com.example.backend.enums.Priority;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequest {
	@NotBlank(message = "Title is required")
    private String title;

    private String description;

    private Priority priority;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "TaskRequest [title=" + title + ", description=" + description + ", priority=" + priority
				+ ", getTitle()=" + getTitle() + ", getDescription()=" + getDescription() + ", getPriority()="
				+ getPriority() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public TaskRequest(@NotBlank(message = "Title is required") String title, String description, Priority priority) {
		super();
		this.title = title;
		this.description = description;
		this.priority = priority;
	}

	public TaskRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
