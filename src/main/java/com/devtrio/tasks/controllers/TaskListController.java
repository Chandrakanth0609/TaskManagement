package com.devtrio.tasks.controllers;

import com.devtrio.tasks.domain.dto.TaskListDto;
import com.devtrio.tasks.domain.entities.TaskList;
import com.devtrio.tasks.mappers.TaskListMapper;
import com.devtrio.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
       TaskList createdTaskList= taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);

    }
    @GetMapping(path = "/{task-list-id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task-list-id")UUID taskListId){
        return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
    }

    @PutMapping(path = "/{task-list-id}")
    public TaskListDto updateTaskList(
            @PathVariable("task-list-id") UUID taskListId,
            @RequestBody TaskListDto taskListDto
    ){
        TaskList updatedTaskList=taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(updatedTaskList);

    }


    @DeleteMapping(path = "/{task-list-id}")
    public void deleteTaskList(@PathVariable("task-list-id") UUID taskListId){
        taskListService.deleteTaskList(taskListId);
    }
}

