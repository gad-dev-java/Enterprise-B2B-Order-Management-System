\c customer_service_db;

CREATE TABLE customers
(
    id             UUID PRIMARY KEY,
    keycloak_id    VARCHAR(255) UNIQUE NOT NULL,
    email          VARCHAR(255) UNIQUE NOT NULL,
    first_name     VARCHAR(100)        NOT NULL,
    last_name      VARCHAR(100)        NOT NULL,
    company_name   VARCHAR(200),
    tax_id         VARCHAR(50),
    business_phone VARCHAR(20),
    address_line   TEXT,
    city           VARCHAR(100),
    country        VARCHAR(100),
    status         VARCHAR(20) DEFAULT 'ACTIVE',
    created_at     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_customer_keycloak ON customers (keycloak_id);
CREATE INDEX idx_customer_email ON customers (email);