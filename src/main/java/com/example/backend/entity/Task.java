package com.example.backend.entity;

import java.time.LocalDateTime;

import com.example.backend.enums.Priority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "tasks")

public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private boolean completed = false;

    private LocalDateTime createdAt;
    
    @ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(
	        name = "task_tags",
	        joinColumns = @JoinColumn(name = "task_id"),
	        inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private Set<Tag> tags = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    
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


	public Task(Long id, String title, String description, Priority priority, boolean completed,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.completed = completed;
		this.createdAt = createdAt;
	}
	

	public Set<Tag> getTags() {
		return tags;
	}


	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}


	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", priority=" + priority
				+ ", completed=" + completed + ", createdAt=" + createdAt + ", getId()=" + getId() + ", getTitle()="
				+ getTitle() + ", getDescription()=" + getDescription() + ", getPriority()=" + getPriority()
				+ ", isCompleted()=" + isCompleted() + ", getCreatedAt()=" + getCreatedAt() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
	
	

}
