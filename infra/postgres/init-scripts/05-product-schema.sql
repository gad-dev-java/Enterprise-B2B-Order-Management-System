\c product_service_db;

CREATE TABLE categories
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE products
(
    id          UUID PRIMARY KEY,
    sku         VARCHAR(50) UNIQUE NOT NULL,
    name        VARCHAR(200)       NOT NULL,
    description TEXT,
    base_price  DECIMAL(10, 2)     NOT NULL,
    category_id INT REFERENCES categories (id),
    status      VARCHAR(20) DEFAULT 'ACTIVE',
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE price_lists
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    currency VARCHAR(3) DEFAULT 'USD'
);

CREATE TABLE price_list_items
(
    id            SERIAL PRIMARY KEY,
    price_list_id INT REFERENCES price_lists (id),
    product_id    UUID REFERENCES products (id),
    custom_price  DECIMAL(10, 2) NOT NULL,
    UNIQUE (price_list_id, product_id)
);