package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.TaskRequest;
import com.example.backend.dto.TaskResponse;
import com.example.backend.enums.Priority;
import com.example.backend.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {

	private final TaskService taskService;
	
	TaskController(TaskService taskService){
		this.taskService=taskService;
	}

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request) {

    	TaskResponse response = taskService.createTask(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
	/*
	 * @GetMapping public List<TaskResponse> getAllTasks() { return
	 * taskService.getAllTasks(); }
	 */
    
    @GetMapping
    public List<TaskResponse> getTasks(

            @RequestParam(name="completed",required = false)
            Boolean completed,

            @RequestParam(name="priority",required = false)
            Priority priority,
            
            @RequestParam(name= "tag",required = false)
            String tag,

            @RequestParam(name="sort",required = false)
            String sort) {

        return taskService.getTasks(
                completed,
                priority,
                tag,
                sort);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(
            @PathVariable("id") Long id) {

        return taskService.getTaskById(id);
    }
    
    @PutMapping("/{id}")
    public TaskResponse updateTask(
            @PathVariable("id") Long id,
            @Valid @RequestBody TaskRequest request) {

        return taskService.updateTask(id, request);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id) {

        taskService.deleteTask(id);
    }
    
    @PatchMapping("/{id}/complete")
    public TaskResponse markTaskCompleted(
            @PathVariable("id") Long id) {

        return taskService.markTaskCompleted(id);
    }
    
    @PostMapping("/{taskId}/tags/{tagId}")
    public TaskResponse assignTagToTask(
            @PathVariable("taskId") Long taskId,
            @PathVariable("tagId") Long tagId) {

        return taskService.assignTagToTask(
                taskId,
                tagId);
    }

}
