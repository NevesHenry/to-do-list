CREATE TABLE task (
    id INT PRIMARY KEY UNIQUE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status  VARCHAR(15) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active TINYINT(1) NOT NULL
);