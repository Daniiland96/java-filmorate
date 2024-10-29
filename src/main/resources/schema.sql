CREATE TABLE IF NOT EXISTS rating_MPA (
id serial not null primary KEY,
name varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS films (
id bigserial not null primary KEY,
name varchar NOT NULL,
description varchar,
release_date timestamp,
duration bigint,
rating_id int not null references rating_MPA(id)
);

CREATE TABLE IF NOT EXISTS genres (
id serial not null primary KEY,
name varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS film_genres (
film_id bigint not NULL references films(id),
genre_id int not NULL references genres(id)
);

CREATE TABLE IF NOT EXISTS users (
id bigserial not null primary KEY,
name varchar NOT NULL,
email varchar NOT NULL,
login varchar NOT NULL,
birthday timestamp
);

CREATE TABLE IF NOT EXISTS user_friends (
user_id bigint not NULL references users(id),
friend_id bigint not NULL references users(id),
status boolean
);

CREATE TABLE IF NOT EXISTS film_likes (
film_id bigint not NULL references films(id),
user_liked_id bigint not NULL references users(id)
);

