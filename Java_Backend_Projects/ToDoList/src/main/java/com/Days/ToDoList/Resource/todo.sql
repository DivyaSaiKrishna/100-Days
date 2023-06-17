CREATE TABLE todo (
    id SERIAL PRIMARY KEY,
    toDoString VARCHAR(255) NOT NULL,
    insertDate DATE NOT NULL,
    isDeleted BOOLEAN NOT NULL,
    isCompleted BOOLEAN NOT NULL,
    completedDate DATE
);
