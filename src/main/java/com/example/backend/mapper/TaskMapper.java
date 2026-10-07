package com.example.backend.mapper;

import com.example.backend.dto.TaskRequest;
import com.example.backend.dto.TaskResponse;
import com.example.backend.entity.Tag;
import com.example.backend.entity.Task;
import java.util.Set;
import java.util.HashSet;

public class TaskMapper {
	
	private TaskMapper() {
    }

    public static Task toEntity(TaskRequest request) {

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());

        return task;
    }

    public static TaskResponse toResponse(Task task) {

        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setPriority(task.getPriority());
        response.setCompleted(task.isCompleted());
        response.setCreatedAt(task.getCreatedAt());
        
        Set<String> tagNames = new HashSet<>();
        
        for (Tag tag : task.getTags()) {
            tagNames.add(tag.getName());
        }

        response.setTags(tagNames);

        return response;
    }

}
