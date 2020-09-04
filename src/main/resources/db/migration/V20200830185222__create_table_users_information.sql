CREATE TABLE users_information (
    user_id INTEGER PRIMARY KEY REFERENCES users (id),
    full_name VARCHAR(255) NOT NULL
);

