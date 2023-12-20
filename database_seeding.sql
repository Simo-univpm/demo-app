-- Lo script viene eseguito quando viene creato il db per la prima volta



DROP TABLE IF EXISTS registered_users;
DROP TABLE IF EXISTS last_access;
DROP TABLE IF EXISTS user_activity;

-- Tabella che memorizza gli utenti registrati
CREATE TABLE registered_users(

	id SERIAL PRIMARY KEY NOT NULL,
	username VARCHAR(20) NOT NULL,
	passwd VARCHAR(20) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL

);


-- Tabella che memorizza la data di registrazione/ultimo accesso di un utente
CREATE TABLE last_access(

	id SERIAL PRIMARY KEY NOT NULL,
	user_id INT NOT NULL,
	last_access TIMESTAMP NOT NULL

);


-- Tabella popolata dal listener kafka
CREATE TABLE user_activity(

	id SERIAL PRIMARY KEY NOT NULL,
	activity_partition VARCHAR(20) NOT NULL,
	activity_key VARCHAR(20) NOT NULL,
	username VARCHAR(20) NOT NULL,
	activity_date TIMESTAMP NOT NULL

);


INSERT INTO REGISTERED_USERS(username, passwd, first_name, last_name)
VALUES
	('user1', '12345678', 'aaa', 'aaa'),
	('user2', '12345678', 'bbb', 'bbb'),
	('user3', '12345678', 'ccc', 'ccc');