package dev.lucas.todoapp.todo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.lucas.todoapp.todo.exceptions.TodoNotFoundException;
import jakarta.annotation.PostConstruct;

@Repository
public class TodoRepository {

    private List<Todo> todos = new ArrayList<>();

    public List<Todo> findAll() {
        return todos;
    }

    public Optional<Todo> findById(Integer id) {
        return todos.stream().filter(t -> t.id().equals(id)).findFirst();
    }

    public Todo update(TodoUpdate todoUpdate, Integer id) {
        Optional<Todo> todoOptional = todos.stream().filter(t -> t.id().equals(id)).findFirst();
        System.out.println(todoOptional);
        if (todoOptional.isPresent()) {

            Todo existingTodo = todoOptional.get();

            String newTitle = todoUpdate.title().orElse(existingTodo.title());
            String newDescription = todoUpdate.description().orElse(existingTodo.description());
            Boolean newCompleted = todoUpdate.completed().orElse(existingTodo.completed());
            LocalDateTime newDueDate = todoUpdate.dueDate().orElse(existingTodo.dueDate());

            Todo updatedTodo = new Todo(id, newTitle, newDescription, newCompleted, newDueDate,
                    existingTodo.createdAt(), LocalDateTime.now());

            todos.set(todos.indexOf(existingTodo), updatedTodo);

            return updatedTodo;
        }

        throw new TodoNotFoundException(id);
    }

    public void deleteById(Integer id) {
        todos.removeIf(t -> t.id().equals(id));
    }

    @PostConstruct
    public void init() {
        todos.add(new Todo(1, "Workout", "Morning Routine", false, LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                LocalDateTime.now(), LocalDateTime.now()));
        todos.add(new Todo(2, "Meditate", "Alone Time", false, LocalDateTime.now().plus(2, ChronoUnit.HOURS),
                LocalDateTime.now(), LocalDateTime.now()));

    }
}
