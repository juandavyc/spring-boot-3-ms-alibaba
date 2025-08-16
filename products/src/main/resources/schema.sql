CREATE TABLE IF NOT EXISTS products
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    code        VARCHAR(50) UNIQUE NOT NULL,
    name        VARCHAR(150)       NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2)     NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    deleted TINYINT DEFAULT 0
);