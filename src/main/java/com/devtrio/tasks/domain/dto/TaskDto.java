package com.devtrio.tasks.domain.dto;

import com.devtrio.tasks.domain.entities.TaskPriority;
import com.devtrio.tasks.domain.entities.TaskStatus;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
