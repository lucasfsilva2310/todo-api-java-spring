CREATE TABLE IF NOT EXISTS Todos (
    id INT NOT NULL AUTO_INCREMENT,
    title varchar(250) NOT NULL,
    description varchar(250),
    completed boolean NOT NULL,
    due_date timestamp NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    PRIMARY KEY (id)
);