package dev.lucas.todoapp.todo;

import java.time.LocalDateTime;
import java.util.Optional;

public record TodoUpdate(
        Optional<String> title,
        Optional<String> description,
        Optional<Boolean> completed,
        Optional<LocalDateTime> dueDate) {

}
