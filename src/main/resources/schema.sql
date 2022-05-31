DROP TABLE IF EXISTS transactions CASCADE;

CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    amount NUMERIC(6,2) NOT NULL,
    issued_at timestamp NOT NULL
);

INSERT INTO transactions (order_id,username,amount,issued_at) VALUES ( 1, 'MarioRossi', 50.00, '2022-05-30 16:00:00.000000');
INSERT INTO transactions (order_id,username,amount,issued_at) VALUES ( 2, 'JohnDoe', 20.00, '2022-05-31 10:00:00.000000');
INSERT INTO transactions (order_id,username,amount,issued_at) VALUES ( 3, 'MarioRossi', 30.00, '2022-06-01 20:00:00.000000');