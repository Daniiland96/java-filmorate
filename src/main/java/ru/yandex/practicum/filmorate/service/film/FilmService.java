package ru.yandex.practicum.filmorate.service.film;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;

@Slf4j
@Service
@AllArgsConstructor
public class FilmService {
    private UserStorage userStorage;
    private FilmStorage filmStorage;

    public Film addLike(Long filmId, Long userId) {
        Film film = filmStorage.findById(filmId);
        User user = userStorage.findById(userId);
        film.getUsersLikes().add(user.getId());
        log.info("Количество лайков: {}", film.getUsersLikes().size());
        return film;
    }

    public Film deleteLike(Long filmId, Long userId) {
        Film film = filmStorage.findById(filmId);
        User user = userStorage.findById(userId);
        if (film.getUsersLikes().contains(user.getId())) {
            film.getUsersLikes().remove(user.getId());
            log.info("Количество лайков: {}", film.getUsersLikes().size());
            return film;
        }
        throw new NotFoundException("Лайк пользователя с id = " + user.getId() + " не найден");
    }

    public Collection<Film> getPopularFilm(Long count) {
        return filmStorage.findAll().stream()
                .sorted((f1, f2) -> Integer.compare(f2.getUsersLikes().size(), f1.getUsersLikes().size()))
                .limit(count)
                .toList();
    }


}
