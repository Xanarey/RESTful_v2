CREATE TABLE IF NOT EXISTS postgres.users
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);