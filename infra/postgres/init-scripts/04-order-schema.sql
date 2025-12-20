\c order_service_db;

CREATE TABLE orders
(
    id            UUID PRIMARY KEY,
    customer_id   UUID           NOT NULL,
    total_amount  DECIMAL(10, 2) NOT NULL,
    status        VARCHAR(20)    NOT NULL,
    error_message TEXT,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items
(
    id         SERIAL PRIMARY KEY,
    order_id   UUID REFERENCES orders (id),
    product_id UUID           NOT NULL,
    quantity   INT            NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    subtotal   DECIMAL(10, 2) NOT NULL
);