package com.example.backend.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.backend.dto.TaskRequest;
import com.example.backend.dto.TaskResponse;
import com.example.backend.enums.Priority;

public interface TaskService {
	TaskResponse createTask(TaskRequest request);
	
//	 List<TaskResponse> getAllTasks();

	 TaskResponse getTaskById(Long id);
	 
	 TaskResponse updateTask(Long id, TaskRequest request);

	 void deleteTask(Long id);

	 TaskResponse markTaskCompleted(Long id);
	 
	 List<TaskResponse> getTasks(
		        Boolean completed,
		        Priority priority,
		        String tag,
		        String sort);
	 
	 TaskResponse assignTagToTask(Long taskId, Long tagId);

}
