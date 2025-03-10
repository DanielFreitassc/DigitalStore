CREATE TABLE products (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    image_id UUID UNIQUE NOT NULL,
    price NUMERIC(15,2) CHECK(price >= 0) NOT NULL,
    created_at TIMESTAMP NOT NULL
)