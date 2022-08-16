CREATE TABLE IF NOT EXISTS users (
    user_id INTEGER GENERATED ALWAYS AS IDENTITY,
    email VARCHAR(256) UNIQUE NOT NULL,
    email_verified BOOLEAN DEFAULT FALSE,
    password VARCHAR(256) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS email_verification_tokens (
    token_id INTEGER GENERATED ALWAYS AS IDENTITY,
    token TEXT NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    PRIMARY KEY (token_id)
);

CREATE TABLE IF NOT EXISTS groups (
    group_id INTEGER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(32) NOT NULL,
    PRIMARY KEY (group_id)
);

CREATE TABLE IF NOT EXISTS group_members (
    group_member_id INTEGER GENERATED ALWAYS AS IDENTITY,
    is_admin BOOLEAN DEFAULT FALSE,
    user_id INTEGER NOT NULL,
    group_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (group_id) REFERENCES groups (group_id),
    PRIMARY KEY (group_member_id)
);

CREATE TABLE IF NOT EXISTS categories (
    category_id INTEGER GENERATED ALWAYS AS IDENTITY,
    user_id INTEGER NOT NULL,
    name VARCHAR(32) NOT NULL CHECK (name != 'Complete'),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS tasks (
    task_id INTEGER GENERATED ALWAYS AS IDENTITY,
    user_id INTEGER NOT NULL,
    name VARCHAR(32) NOT NULL,
    is_done BOOLEAN DEFAULT FALSE,
    group_id INTEGER,
    category_id INTEGER,
    complete_by DATE,
    description TEXT,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (group_id) REFERENCES groups (group_id),
    FOREIGN KEY (category_id) REFERENCES categories (category_id),
    PRIMARY KEY (task_id)
);

CREATE TABLE IF NOT EXISTS reminders (
    reminder_id INTEGER GENERATED ALWAYS AS IDENTITY,
    task_id INTEGER NOT NULL,
    remind_by DATE NOT NULL,
    repeat_every INTEGER, -- seconds
    FOREIGN KEY (task_id) REFERENCES tasks (task_id),
    PRIMARY KEY (reminder_id)
);

CREATE TABLE IF NOT EXISTS SPRING_SESSION (
	PRIMARY_ID CHAR(36) NOT NULL,
	SESSION_ID CHAR(36) NOT NULL,
	CREATION_TIME BIGINT NOT NULL,
	LAST_ACCESS_TIME BIGINT NOT NULL,
	MAX_INACTIVE_INTERVAL INT NOT NULL,
	EXPIRY_TIME BIGINT NOT NULL,
	PRINCIPAL_NAME VARCHAR(100),
	CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX IF NOT EXISTS SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 IF NOT EXISTS ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 IF NOT EXISTS ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE IF NOT EXISTS SPRING_SESSION_ATTRIBUTES (
	SESSION_PRIMARY_ID CHAR(36) NOT NULL,
	ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
	ATTRIBUTE_BYTES BYTEA NOT NULL,
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);