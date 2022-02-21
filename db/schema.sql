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
);
CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);