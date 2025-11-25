package com.devtrio.tasks.mappers.impl;

import com.devtrio.tasks.domain.dto.TaskDto;
import com.devtrio.tasks.domain.entities.Task;
import com.devtrio.tasks.domain.entities.TaskList;
import com.devtrio.tasks.mappers.TaskMappers;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMappers {

    @Override
    public Task fromDto(TaskDto taskDto) {
        return new  Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}