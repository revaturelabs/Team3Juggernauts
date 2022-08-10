CREATE TABLE IF NOT EXISTS users (
    user_id INTEGER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(32) NOT NULL,
    email VARCHAR(256) UNIQUE NOT NULL,
    password VARCHAR(256) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS teams (
    team_id INTEGER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(32) NOT NULL,
    PRIMARY KEY (team_id)
);

CREATE TABLE IF NOT EXISTS teammates (
    teammate_id INTEGER GENERATED ALWAYS AS IDENTITY,
    is_admin BOOLEAN DEFAULT FALSE,
    user_id INTEGER NOT NULL,
    team_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (team_id) REFERENCES teams (team_id),
    PRIMARY KEY (teammate_id)
);

CREATE TABLE IF NOT EXISTS tasks (
    task_id INTEGER GENERATED ALWAYS AS IDENTITY,
    user_id INTEGER NOT NULL,
    name VARCHAR(32) NOT NULL,
    category VARCHAR(32) NOT NULL,
    complete_by DATE NOT NULL,
    is_done BOOLEAN DEFAULT FALSE,
    description TEXT,
    repeats_every INTEGER,
    remind_by INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
