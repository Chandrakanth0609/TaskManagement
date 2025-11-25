package com.devtrio.tasks.controllers;

import com.devtrio.tasks.domain.dto.TaskDto;
import com.devtrio.tasks.domain.entities.Task;
import com.devtrio.tasks.mappers.TaskMappers;
import com.devtrio.tasks.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists/{task-list-id}/tasks")
public class TasksController {
    private final TaskService taskService;
    private final TaskMappers taskMappers;

    public TasksController(TaskService taskService, TaskMappers taskMappers) {
        this.taskService = taskService;
        this.taskMappers = taskMappers;
    }
    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task-list-id")UUID taskListId){
        return  taskService.listTasks(taskListId)
                .stream()
                .map(taskMappers::toDto)
                .toList();
    }
    @PostMapping
    public TaskDto createTask(
            @PathVariable("task-list-id") UUID taskListId,
            @RequestBody TaskDto taskDto){
        Task createdTask=taskService.createTask(
                taskListId,
                taskMappers.fromDto(taskDto)
        );
        return taskMappers.toDto(createdTask);

    }
    @GetMapping(path = "/{task-id}")
    public Optional<TaskDto> getTask(
            @PathVariable("task-list-id") UUID taskListId,
            @PathVariable("task-id") UUID taskId
    ){
       return taskService.getTask(taskListId,taskId).map(taskMappers::toDto);

    }
    @PutMapping(path = "/{task-id}")
    public TaskDto updatetask(
            @PathVariable("task-list-id") UUID taskListId,
            @PathVariable("task-id") UUID taskId,
            @RequestBody TaskDto taskDto
    ){
        Task updatedTask=taskService.updateTask(
                taskListId,
                taskId,
                taskMappers.fromDto(taskDto)
        );
        return taskMappers.toDto(updatedTask);
    }
    @DeleteMapping(path = "/{task-id}")
    public void deleteTask(
            @PathVariable("task-list-id") UUID taskListId,
            @PathVariable("task-id") UUID taskId
            ){
        taskService.deleteTask(taskListId,taskId);
    }
}
