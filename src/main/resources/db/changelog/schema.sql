-- Create customer table
CREATE TABLE customer (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

-- Create order table
CREATE TABLE "order" (
                         id BIGSERIAL PRIMARY KEY,
                         description VARCHAR(255) NOT NULL,
                         customer_id BIGINT NOT NULL,
                         CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE INDEX idx_customer_name ON customer(name);
CREATE INDEX idx_order_customer_id ON "order"(customer_id);

CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE order_product (
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES "order"(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);