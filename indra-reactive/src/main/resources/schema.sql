CREATE TABLE IF NOT EXISTS brand (
    id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product (
    id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS price_rate (
    id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS price (
    id INT NOT NULL,
    curr VARCHAR(255),
    end_date TIMESTAMP,
    price DECIMAL(19, 2),
    priority INTEGER,
    start_date TIMESTAMP,
    brand_id INT,
    price_list INT,
    product_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (brand_id) REFERENCES brand(id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (price_list) REFERENCES price_rate(id)
);

