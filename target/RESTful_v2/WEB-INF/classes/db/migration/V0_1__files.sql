CREATE TABLE IF NOT EXISTS postgres.files
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    url character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT files_pkey PRIMARY KEY (id)
);