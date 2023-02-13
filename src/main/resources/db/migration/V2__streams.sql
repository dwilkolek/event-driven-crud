ALTER TABLE event
    ADD COLUMN stream_id uuid;

CREATE TABLE event_stream (
    id uuid NOT NULL UNIQUE,
    version bigint NOT NULL,
    stream_id text NOT NULL unique
);
