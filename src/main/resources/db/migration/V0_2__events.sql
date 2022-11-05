CREATE TABLE IF NOT EXISTS postgres.events
(
    id integer NOT NULL,
    created character varying COLLATE pg_catalog."default" NOT NULL,
    updated character varying COLLATE pg_catalog."default",
    user_id integer NOT NULL,
    file_id integer NOT NULL,
    CONSTRAINT events_pkey PRIMARY KEY (id),
    CONSTRAINT file_id_fk FOREIGN KEY (file_id)
        REFERENCES postgres.files (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id)
        REFERENCES postgres.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)