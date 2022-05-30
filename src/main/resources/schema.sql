DROP TABLE IF EXISTS transactions CASCADE;

CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    amount NUMERIC(6,2) NOT NULL,
    issued_at timestamp NOT NULL
);

INSERT INTO transactions VALUES (1, 1, 'MarioRossi', 50.00, '2022-05-30 16:00:00.000000');