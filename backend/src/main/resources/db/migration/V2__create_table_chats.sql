CREATE TABLE chats (
    id UUID PRIMARY KEY,
    message VARCHAR(255),
    username VARCHAR(100),
    status VARCHAR(20),
    created_at TIMESTAMP NOT NULL
);
