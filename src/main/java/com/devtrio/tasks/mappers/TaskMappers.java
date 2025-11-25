package com.devtrio.tasks.mappers;

import com.devtrio.tasks.domain.dto.TaskDto;
import com.devtrio.tasks.domain.entities.Task;

public interface TaskMappers {
    Task fromDto (TaskDto taskDto);
    TaskDto toDto(Task task );
}
