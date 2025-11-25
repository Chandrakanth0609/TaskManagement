package com.devtrio.tasks.mappers;

import com.devtrio.tasks.domain.dto.TaskDto;
import com.devtrio.tasks.domain.dto.TaskListDto;
import com.devtrio.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
