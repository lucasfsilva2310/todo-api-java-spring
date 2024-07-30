package dev.lucas.todoapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartServer implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(StartServer.class);

    public StartServer() {
    }

    // Server Config before start
    // Needs to be named run because it will override CommandlineRunner.run method
    @Override
    public void run(String... args) throws Exception {
        LOG.info("Application configured successfully.");
    }
}
