package dev.lucas.todoapp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.lucas.todoapp.todo.Todo;

@Component
public class StartServer implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(StartServer.class);

    public StartServer() {
    }

    // Server Config before start
    // Needs to be named run because it will override CommandlineRunner.run method
    @Override
    public void run(String... args) throws Exception {
        Todo todo = new Todo(1, "Gym", "Morning Workout", false, LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                LocalDateTime.now(), LocalDateTime.now());

        LOG.info("First Todo: {}", todo.toString());
        LOG.info("Application configured successfully.");
    }
}
