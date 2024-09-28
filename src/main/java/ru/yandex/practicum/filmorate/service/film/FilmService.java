package ru.yandex.practicum.filmorate.service.film;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FilmService {
    private UserStorage userStorage;
    private FilmStorage filmStorage;

    public Film addLike(Long filmId, Long userId) {
        Film film = filmStorage.findById(filmId);
        User user = userStorage.findById(userId);
        userId = user.getId();
        film.addLike(userId);
        return film;
    }
}
