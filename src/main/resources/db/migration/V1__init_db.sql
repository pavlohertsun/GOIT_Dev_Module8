CREATE TABLE worker(
    id BIGINT PRIMARY KEY,
    name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2),
    birthday DATE CHECK (birthday > '1990-01-01'),
    level VARCHAR(10) NOT NULL CHECK (level IN ('Trainee', 'Junior', 'Middle', 'Senior')),
    salary NUMERIC CHECK (salary >= 100 AND salary <= 100000)
);

CREATE TABLE client(
    id BIGINT PRIMARY KEY,
    name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2)
);

CREATE TABLE project(
    id BIGINT PRIMARY KEY,
    client_id BIGINT,
    start_date DATE,
    finish_date DATE
);

ALTER TABLE project
ADD CONSTRAINT client_id_fk
FOREIGN KEY (client_id)
REFERENCES client(id);

CREATE TABLE project_worker(
    project_id BIGINT NOT NULL,
    worker_id BIGINT NOT NULL,
    PRIMARY KEY(project_id, worker_id),
    FOREIGN KEY(project_id) REFERENCES project(id),
    FOREIGN KEY(worker_id) REFERENCES worker(id)
);

CREATE SEQUENCE workers_sequence START 1;
CREATE SEQUENCE clients_sequence START 1;
CREATE SEQUENCE projects_sequence START 1;

ALTER TABLE worker ALTER COLUMN id SET DEFAULT nextval('workers_sequence');
ALTER TABLE client ALTER COLUMN id SET DEFAULT nextval('clients_sequence');
ALTER TABLE project ALTER COLUMN id SET DEFAULT nextval('projects_sequence');