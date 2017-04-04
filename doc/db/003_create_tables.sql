CREATE TABLE admin
(
  id serial NOT NULL,
  account text NOT NULL,
  name text,
  auth text,
  permission text,
  creator_id integer,
  status integer NOT NULL,
  create_timestamp timestamp without time zone,
  modify_timestamp timestamp without time zone,
  CONSTRAINT admin_pkey PRIMARY KEY (id),
  CONSTRAINT admin_account_key UNIQUE (account)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE admin
  OWNER TO appstore;
INSERT INTO admin VALUES (0, 'root', 'root', '96e79218965eb72c92a549dd5a330112', NULL, 0, 0, now(), now());