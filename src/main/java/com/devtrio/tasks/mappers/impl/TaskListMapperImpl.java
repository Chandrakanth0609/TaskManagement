package com.devtrio.tasks.mappers.impl;

import com.devtrio.tasks.domain.dto.TaskListDto;
import com.devtrio.tasks.domain.entities.Task;
import com.devtrio.tasks.domain.entities.TaskList;
import com.devtrio.tasks.domain.entities.TaskStatus;
import com.devtrio.tasks.mappers.TaskListMapper;
import com.devtrio.tasks.mappers.TaskMappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class TaskListMapperImpl implements TaskListMapper {
    private final TaskMappers taskMappers;

    public TaskListMapperImpl(TaskMappers taskMappers) {
        this.taskMappers = taskMappers;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks-> tasks.stream()
                                .map(taskMappers::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListPrograss(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks ->
                                tasks.stream().map(taskMappers::toDto).toList()
                        ).orElse(null)

        );
    }

    private  Double calculateTaskListPrograss(List<Task> tasks){
        if(null == tasks){
            return null;
        }
        long closedTaskCount= tasks.stream().filter(task ->
                TaskStatus.CLOSED == task.getStatus()
        ).count();


        return (double) closedTaskCount / tasks.size();
    }
}
