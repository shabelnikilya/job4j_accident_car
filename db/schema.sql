CREATE TABLE IF NOT EXISTS type (
    id SERIAL PRIMARY KEY,
    name TEXT
);
CREATE TABLE IF NOT EXISTS accident (
    id SERIAL PRIMARY KEY,
    name TEXT,
    text TEXT,
    address TEXT,
    type_id INT NOT NULL REFERENCES type(id)
);
CREATE TABLE IF NOT EXISTS rule (
    id SERIAL PRIMARY KEY,
    name TEXT
);
CREATE TABLE IF NOT EXISTS accidents_rules (
    id SERIAL PRIMARY KEY,
    accident_id INT NOT NULL REFERENCES accident(id),
    rule_id INT NOT NULL REFERENCES RULE(id)
)