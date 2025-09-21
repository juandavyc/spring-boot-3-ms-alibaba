CREATE TABLE IF NOT EXISTS orders (
    id UUID PRIMARY KEY,
    account_id UUID NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    updated_by VARCHAR(100) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS order_items (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id)
);
