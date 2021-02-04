drop database notedb;
drop user noteapi;
create user noteapi with password 'password';
create database notedb with template=template0 owner noteapi;
\c notedb;
alter default privileges grant all on tables to noteapi;
alter default privileges grant all on sequences to noteapi;

create table note_table (
    note_id INTEGER PRIMARY KEY NOT NULL,
    title VARCHAR(30) NOT NULL,
    note_created BIGINT NOT NULL,
    note_text TEXT NOT NULL
);

create sequence note_table_seq increment 1 start 1;


