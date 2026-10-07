package com.example.backend.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.backend.enums.Priority;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {
	

    private Long id;

    private String title;

    private String description;

    private Priority priority;

    private boolean completed;

    private LocalDateTime createdAt;
    
    private Set<String> tags;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "TaskResponse [id=" + id + ", title=" + title + ", description=" + description + ", priority=" + priority
				+ ", completed=" + completed + ", createdAt=" + createdAt + ", getId()=" + getId() + ", getTitle()="
				+ getTitle() + ", getDescription()=" + getDescription() + ", getPriority()=" + getPriority()
				+ ", isCompleted()=" + isCompleted() + ", getCreatedAt()=" + getCreatedAt() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public TaskResponse(Long id, String title, String description, Priority priority, boolean completed,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.completed = completed;
		this.createdAt = createdAt;
	}

	public TaskResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    

}
