CREATE TABLE IF NOT EXISTS Todos (
    id SERIAL PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    description VARCHAR(250),
    completed BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);