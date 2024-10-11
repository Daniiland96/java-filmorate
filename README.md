# java-filmorate
Template repository for Filmorate project. 
### DataBase
![DBdiagram](/src/main/resources/images/DB.png)
#### Описание:
#### films
Содержит данные о фильмах.

Таблица включает такие поля:
- id - id фильма в формате bigint.
- name - название фильма в формате varchar.
- description - описание фильма в формате varchar.
- releaseDate - дата выхода фильма в формате date.
- duration - длительность фильма в формате interval.
- rating_id - id рейтинга фильма в формате integer.
#### film_likes
Содержит id пользователей, которым понравился фильм.

Таблица включает такие поля:
- film_id - id фильма в формате bigint.
- user_liked_id - id пользователя, которому понравился фильм в формате bigint.
#### film_genres
Содержит id жанров фильма.

Таблица включает такие поля:
- film_id - id фильма в формате bigint.
- genre_id - id жанра в формате integer.
#### genre
Содержит названия жанров. 

Таблица включает такие поля:
- id - id жанра в формате integer.
- name - название жанра в формате varchar.
#### rating_MPA
Содержит названия рейтингов по стандарту MPA.

Таблица включает такие поля:
- id - id рейтинга в формате integer.
- name - название жанра в формате varchar.
#### users
Содержит данные о пользователях.

Таблица включает такие поля:
- id - id пользователя в формате bigint.
- email - электронная почта пользователя в формате varchar.
- login - логин пользователя в формате varchar.
- name - имя пользователя в формате varchar.
- birthday - день рождения пользователя в формате date.
#### user_friends
Содержит id друзей пользователей.

Таблица включает такие поля:
- user_id - id пользователя в формате bigint.
- friend_id - id друзей пользователя в формате bigint.
- status_id - id статуса добавления нового друга в формате integer.
#### friendship_status 
Содержит статусы добавления новых друзей.

Таблица включает такие поля:
- status_id - id статуса в формате integer.
- status - название статуса в формате boolean.

Примеры запросов:
- Получить данные о всех фильмах:
```SELECT * FROM films;```
- Получить данные о фильме по его id:
```SELECT * FROM films WHERE films.id = 1;```
- Получить данные о топ N популярных фильмах:
```
  SELECT f.name, COUNT(fl.user_liked_id) AS likes
  FROM films AS f 
  LEFT JOIN film_likes AS fl ON f.id = fl.film_id
  GROUP BY f.id
  ORDER BY likes DESC LIMIT N; 
```
- Получить данные о пользователе по его id:
```SELECT * FROM users WHERE users.id = 1;```
- Получить данные об общих друзьях пользователей:
```
SELECT friend_id
FROM user_friends
WHERE user_id = 1
AND status_id = 1
AND (SELECT friend_id
     FROM user_friends
     WHERE user_id = 2
     AND status_id = 1 ) = friend_id;
```