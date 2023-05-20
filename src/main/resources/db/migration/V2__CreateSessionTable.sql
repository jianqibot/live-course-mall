
-- one user_id can be mapped to many session
-- for example, one user can log in via many devices
-- like smart phone, laptop etc
CREATE TABLE session(
    id serial PRIMARY KEY,
    cookie VARCHAR(50) UNION NOT NULL,
    user_id INT NOT NULL
)