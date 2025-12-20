\c billing_service_db;

CREATE TABLE invoices
(
    id                    UUID PRIMARY KEY,
    order_id              UUID UNIQUE    NOT NULL,
    customer_id           UUID           NOT NULL,
    amount                DECIMAL(10, 2) NOT NULL,
    status                VARCHAR(20)    NOT NULL,
    transaction_reference VARCHAR(100),
    issued_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


