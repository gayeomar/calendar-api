--Create an Oracle realm
SET DATABASE SQL SYNTAX ORA TRUE;

--User
CREATE TABLE IF NOT EXISTS user(
  id INTEGER PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  email  VARCHAR(50) UNIQUE,
);
ALTER TABLE user ADD CONSTRAINT email_unique unique (email);

INSERT INTO user (id, first_name, last_name, email) VALUES (1, 'Omar', 'Gaye', 'omar@gmail.com');

INSERT INTO user (id, first_name, last_name, email) VALUES (2, 'Bill', 'Smith', 'bill@gmail.com');

--Calendar
CREATE TABLE IF NOT EXISTS calendar(
  name VARCHAR(30) PRIMARY KEY,
  user_id INTEGER
);
ALTER TABLE calendar ADD CONSTRAINT user_unique unique (user_id);

--Event
CREATE TABLE IF NOT EXISTS event(
  id INTEGER PRIMARY KEY,
  title VARCHAR(30),
  location VARCHAR(30),
  calendar_name VARCHAR(30),
  event_date VARCHAR(10),
  start_time VARCHAR(8),
  end_time VARCHAR(8),
  reminder_time VARCHAR(8),
  attendees VARCHAR2(250),
  isReminderSent char(1) CHECK (isReminderSent in ('Y','N'))
);

--User_Api_Key
CREATE TABLE IF NOT EXISTS user_api_key(
  id INTEGER PRIMARY KEY,
  user_id VARCHAR(30),
  api_key VARCHAR(30)
);
ALTER TABLE user_api_key ADD CONSTRAINT user_api_key_unique unique (user_id, api_key);
ALTER TABLE user_api_key ADD CONSTRAINT user_id_unique unique (user_id);

INSERT INTO user_api_key (id, user_id, api_key) VALUES (1, 1, 'key123');
INSERT INTO user_api_key (id, user_id, api_key) VALUES (2, 2, 'key456');



COMMIT WORK;
