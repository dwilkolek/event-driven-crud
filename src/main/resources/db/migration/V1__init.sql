CREATE TABLE event (
    id uuid NOT NULL UNIQUE,
    version bigint NOT NULL,
    data jsonb NOT NULL
);

CREATE TABLE project (
    id uuid NOT NULL UNIQUE,
    version bigint NOT NULL,
    name text NOT NULL,
    description text NOT NULL,
    slug varchar(10) NOT NULL UNIQUE,
    next_task_id bigint NOT NULL
);

CREATE TABLE task (
    id uuid NOT NULL UNIQUE,
    slug text NOT NULL UNIQUE,
    project_id uuid NOT NULL,
    version bigint NOT NULL,
    title text NOT NULL,
    status varchar(100)
);