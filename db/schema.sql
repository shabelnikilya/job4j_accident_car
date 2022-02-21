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
CREATE TABLE IF NOT EXISTS authorities (
  id serial primary key,
  authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE IF NOT EXISTS users (
  id serial primary key,
  username VARCHAR(50) NOT NULL unique,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$bgC6.gH.RqbbcKH3TQwXvOLz5ZeuspXy8Dhe2CEj/UXOg3k1o99jC',
(select id from authorities where authority = 'ROLE_ADMIN'));