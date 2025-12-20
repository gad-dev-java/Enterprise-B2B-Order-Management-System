\c inventory_service_db;

CREATE TABLE inventory
(
    product_id        UUID PRIMARY KEY,
    quantity          INT NOT NULL CHECK (quantity >= 0),
    reserved_quantity INT       DEFAULT 0,
    last_updated      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE inventory_transactions
(
    id         SERIAL PRIMARY KEY,
    product_id UUID REFERENCES inventory (product_id),
    order_id   UUID,
    type       VARCHAR(20) NOT NULL,
    quantity   INT         NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);