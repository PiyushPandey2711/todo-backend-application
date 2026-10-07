package com.example.backend.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.backend.dto.TaskRequest;
import com.example.backend.dto.TaskResponse;
import com.example.backend.entity.Tag;
import com.example.backend.entity.Task;
import com.example.backend.enums.Priority;
import com.example.backend.exception.TagNotFoundException;
import com.example.backend.exception.TaskNotFoundException;
import com.example.backend.mapper.TaskMapper;
import com.example.backend.repository.TagRepository;
import com.example.backend.repository.TaskRepository;
import com.example.backend.specification.TaskSpecification;

import jakarta.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
	
	private final TaskRepository taskRepository;
	private final TagRepository tagRepository;
	
	TaskServiceImpl(TaskRepository taskRepository, TagRepository tagRepository){
		this.tagRepository=tagRepository;
		this.taskRepository=taskRepository;
	}
// Add a Task
	@Override
	public TaskResponse createTask(TaskRequest request) {
		
	    // Instantiate Task using standard setters
		Task task = TaskMapper.toEntity(request);

		Task savedTask = taskRepository.save(task);

		return TaskMapper.toResponse(savedTask);
	}
	
//Get all Task
	/*
	 * @Override public List<TaskResponse> getAllTasks() {
	 * 
	 * List<Task> tasks = taskRepository.findAll();
	 * 
	 * return tasks.stream() .map(TaskMapper::toResponse) .toList(); }
	 */
	
//Get Task by id
	@Override
	public TaskResponse getTaskById(Long id) {

	    Task task = taskRepository.findById(id)
	            .orElseThrow(() ->
	                    new TaskNotFoundException(
	                            "Task not found with id " + id));

	    return TaskMapper.toResponse(task);
	}
	
	@Override
	public TaskResponse updateTask(Long id, TaskRequest request) {

	    Task task = taskRepository.findById(id)
	            .orElseThrow(() ->
	                    new TaskNotFoundException(
	                            "Task not found with id " + id));

	    task.setTitle(request.getTitle());
	    task.setDescription(request.getDescription());
	    task.setPriority(request.getPriority());

	    Task updatedTask = taskRepository.save(task);

	    return TaskMapper.toResponse(updatedTask);
	}
	
	@Override
	public void deleteTask(Long id) {

	    Task task = taskRepository.findById(id)
	            .orElseThrow(() ->
	                    new TaskNotFoundException(
	                            "Task not found with id " + id));

	    taskRepository.delete(task);
	}
	
	@Override
	public TaskResponse markTaskCompleted(Long id) {

	    Task task = taskRepository.findById(id)
	            .orElseThrow(() ->
	                    new TaskNotFoundException(
	                            "Task not found with id " + id));
	    if(task.isCompleted()) {
	        return TaskMapper.toResponse(task);
	    }
	    
	    task.setCompleted(true);

	    Task updatedTask = taskRepository.save(task);

	    return TaskMapper.toResponse(updatedTask);
	}
	
	@Override
	public List<TaskResponse> getTasks(
	        Boolean completed,
	        Priority priority,
	        String tag,
	        String sort) {

	    Specification<Task> specification =
	            Specification.where(
	                    TaskSpecification.hasCompleted(completed))
	                    .and(
	                    TaskSpecification.hasPriority(priority))
	                    .and(
	                    TaskSpecification.hasTag(tag));

	    List<Task> tasks =
	            taskRepository.findAll(specification);

	    applySorting(tasks, sort);

	    return tasks.stream()
	            .map(TaskMapper::toResponse)
	            .toList();
	}
	
	private void applySorting(
	        List<Task> tasks,
	        String sort) {

	    if (sort == null || sort.equalsIgnoreCase("newest")) {

	        tasks.sort(
	                Comparator.comparing(Task::getCreatedAt)
	                        .reversed());

	        return;
	    }

	    if (sort.equalsIgnoreCase("alphabetical")) {

	        tasks.sort(
	                Comparator.comparing(Task::getTitle));

	        return;
	    }

	    if (sort.equalsIgnoreCase("priority")) {

	        tasks.sort((t1, t2) ->
	                Integer.compare(
	                        t2.getPriority().ordinal(),
	                        t1.getPriority().ordinal()));
	    }
	}
	
	@Override
	@Transactional
	public TaskResponse assignTagToTask(
	        Long taskId,
	        Long tagId) {

	    Task task = taskRepository.findById(taskId)
	            .orElseThrow(() ->
	                    new TaskNotFoundException(
	                            "Task not found with id " + taskId));

	    Tag tag = tagRepository.findById(tagId)
	            .orElseThrow(() ->
	                    new TagNotFoundException(
	                            "Tag not found with id " + tagId));

	    task.getTags().add(tag);

	    Task updatedTask = taskRepository.save(task);

	    return TaskMapper.toResponse(updatedTask);
	}
	
	
}

