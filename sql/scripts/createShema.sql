CREATE TABLE IF NOT EXISTS artist (
	a_name VARCHAR (80) PRIMARY KEY,
	birthplace VARCHAR ( 50 ) NOT NULL,
	age INT NOT NULL,
	style VARCHAR ( 90 ) NOT NULL
);


CREATE TABLE IF NOT EXISTS artwork (
	title VARCHAR (90) PRIMARY KEY,
	year INT NOT NULL,
	type VARCHAR ( 80 ) NOT NULL,
	price  double precision NOT NULL,
	a_name VARCHAR (80) NOT NULL,
     FOREIGN KEY (a_name) REFERENCES artist (a_name)
);

CREATE TABLE IF NOT EXISTS customer (
	cust_id VARCHAR (80) PRIMARY KEY,
	c_name VARCHAR ( 50 ) NOT NULL,
	address VARCHAR ( 90 ) NOT NULL,
	amount double precision NOT NULL
);


CREATE TABLE IF NOT EXISTS a_group (
	g_name VARCHAR (80) PRIMARY KEY
	
);

CREATE TABLE IF NOT EXISTS classify (
	title VARCHAR (90),
	g_name VARCHAR (80),
	PRIMARY KEY (title, g_name),
     FOREIGN KEY (title) REFERENCES artwork (title),
	FOREIGN KEY (g_name) REFERENCES a_group (g_name)
);

CREATE TABLE IF NOT EXISTS like_group (
	cust_id VARCHAR (80),
	g_name VARCHAR (80),
	PRIMARY KEY (cust_id, g_name),
     FOREIGN KEY (cust_id) REFERENCES customer (cust_id),
	FOREIGN KEY (g_name) REFERENCES a_group (g_name)
);

CREATE TABLE IF NOT EXISTS like_artist (
	cust_id VARCHAR (80),
	a_name VARCHAR (80),
	PRIMARY KEY (cust_id, a_name),
     FOREIGN KEY (cust_id) REFERENCES customer (cust_id),
	FOREIGN KEY (a_name) REFERENCES artist (a_name)
);
