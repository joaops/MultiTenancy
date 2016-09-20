CREATE TABLE public.users (
  username varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  db varchar(45),
  enabled SMALLINT not null default 1,
  CONSTRAINT users_pkey PRIMARY KEY (username)
) WITH (OIDS=FALSE);
ALTER TABLE public.users OWNER TO postgres;
CREATE INDEX idx_username ON public.users USING btree(username);

CREATE TABLE public.test (
  test varchar(45) NOT NULL,
  CONSTRAINT test_pkey PRIMARY KEY (test)
) WITH (OIDS=FALSE);
ALTER TABLE public.test OWNER TO postgres;
CREATE INDEX idx_test ON public.test USING btree(test);

INSERT INTO PUBLIC.USERS (USERNAME, PASSWORD, DB) VALUES ('joao', 'joao', 'master');

INSERT INTO PUBLIC.TEST (TEST) VALUES ('test');