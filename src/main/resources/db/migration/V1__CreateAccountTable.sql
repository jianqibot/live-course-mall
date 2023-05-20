CREATE TABLE "user" (
	id serial PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	encrypted_password VARCHAR ( 100 ) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    status VARCHAR(15) NOT NULL DEFAULT 'OK' -- 'ok' or 'deleted'
);

CREATE TABLE role (
	id serial PRIMARY KEY,
	name VARCHAR ( 50 ) UNIQUE NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    status VARCHAR(15) NOT NULL DEFAULT 'OK' -- 'ok' or 'deleted'
);

-- for @Many to @ Many mapping, we have to create a separate table
-- to store the mapping information
CREATE TABLE user_role (
	id serial PRIMARY KEY,
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    status VARCHAR(15) NOT NULL DEFAULT 'OK' -- 'ok' or 'deleted'
);

-- for @Many to @One mapping, we just need to add the @One's id
-- in the @Many's id
CREATE TABLE permission (
	id serial PRIMARY KEY,
	name VARCHAR ( 50 ) NOT NULL,
	role_id INTEGER NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    status VARCHAR(15) NOT NULL DEFAULT 'OK' -- 'ok' or 'deleted'
);


INSERT INTO "user"(id,username,encrypted_password) VALUES(1,'student1','');
INSERT INTO "user"(id,username,encrypted_password) VALUES(2,'teacher2','');
INSERT INTO "user"(id,username,encrypted_password) VALUES(3,'admin3','');

INSERT INTO user_role(user_id,role_id) VALUES(1,1);
INSERT INTO user_role(user_id,role_id) VALUES(2,2);
INSERT INTO user_role(user_id,role_id) VALUES(3,3);

INSERT INTO role(id,name) VALUES(1,'student');
INSERT INTO role(id,name) VALUES(2,'teacher');
INSERT INTO role(id,name) VALUES(3,'admin');

INSERT INTO permission(name,role_id) VALUES('login',1);

INSERT INTO permission(name,role_id) VALUES('login',2);
INSERT INTO permission(name,role_id) VALUES('upload',2);

INSERT INTO permission(name,role_id) VALUES('login',3);
INSERT INTO permission(name,role_id) VALUES('upload',3);
INSERT INTO permission(name,role_id) VALUES('manage',3);

