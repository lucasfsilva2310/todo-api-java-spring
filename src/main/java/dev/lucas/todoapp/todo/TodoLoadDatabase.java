package dev.lucas.todoapp.todo;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TodoLoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(TodoLoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(TodoRepository todoRepository) {

    return args -> {
      log.info("Preloading Todos... ");

      if (todoRepository.count() > 0) {
        log.info("Todos already loaded. Skipping. ");
      }

      todoRepository.create(new Todo(1, "Wake up", "Just  Wake up", false, LocalDateTime.now(), LocalDateTime.now()));
      todoRepository.create(new Todo(2, "Wake up", "Just  Wake up", false, LocalDateTime.now(), LocalDateTime.now()));

      log.info("TodosLoad complete. ");
    };
  }
}