package dev.lucas.todoapp.todo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import dev.lucas.todoapp.todo.exceptions.TodoNotFoundException;

@Repository
public class TodoRepository {

    private final static Logger LOG = LoggerFactory.getLogger(TodoRepository.class);
    private final JdbcClient jdbcClient;

    public TodoRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void create(Todo todo) {
        var updated = jdbcClient
                .sql("INSERT INTO Todos(title, description, completed, created_at, updated_at) values(?, ?, ?, ?, ?)")
                .params(List.of(todo.title(), todo.description(), todo.completed(), todo.createdAt(),
                        todo.updatedAt()))
                .update();

        Assert.state(updated == 1, "Failed to create todo" + todo.title());
    }

    public List<Todo> findAll() {
        LOG.info("Fetching all todos.");
        return this.jdbcClient.sql("SELECT * FROM Todos").query(Todo.class).list();
    }

    public Optional<Todo> findById(Integer id) {
        LOG.info("Fetching todo with id {}.", id);
        return this.jdbcClient.sql("SELECT * FROM Todos WHERE id = :id").param(id).query(Todo.class).optional();
    }

    public Todo update(TodoUpdate todoUpdate, Integer id) {
        Optional<Todo> todoOptional = this.jdbcClient.sql("SELECT * FROM Todos WHERE id = :id").param(id)
                .query(Todo.class).optional();

        if (todoOptional.isPresent()) {
            LOG.info("Updating todo with id {}.", id);

            this.jdbcClient.sql(
                    "UPDATE Todos SET title = :title, description = :description, completed = :completed,  updated_at = :updated_at WHERE id = :id");

            Todo updatedTodo = todoOptional.get();

            return updatedTodo;
        }

        throw new TodoNotFoundException(id);
    }

    public void deleteById(Integer id) {
        LOG.info("Deleting todo with id {}.", id);
        this.jdbcClient.sql("DELETE FROM Todos WHERE id = :id").param(id);
    }

    public long count() {
        return this.jdbcClient.sql("SELECT COUNT(*) FROM Todos").query((todos, todoNum) -> todos.getLong(1)).single();
    }
}
