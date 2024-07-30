package dev.lucas.todoapp.todo;

import java.time.LocalDateTime;

public record Todo(
                Integer id,
                String title,
                String description,
                Boolean completed,
                LocalDateTime dueDate,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {
}
