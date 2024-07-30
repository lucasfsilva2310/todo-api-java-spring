package dev.lucas.todoapp.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.lucas.todoapp.todo.exceptions.TodoNotFoundException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("")
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Todo> findById(@PathVariable Integer id) {

        Optional<Todo> todo = todoRepository.findById(id);

        if (todo.isEmpty()) {
            throw new TodoNotFoundException(id);
        }

        return todoRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Integer id, @RequestBody TodoUpdate todo) {
        return todoRepository.update(todo, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        todoRepository.deleteById(id);
    }

}
