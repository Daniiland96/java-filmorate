package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {
    private Map<Long, Film> films = new HashMap<>();
    private long filmId = 0;

    @Override
    public Collection<Film> findAll() {
        return films.values();
    }

    @Override
    public Film findById(Long id) {
        if (id == null) {
            throw new ValidationException("Укажите id фильма");
        }
        if (films.containsKey(id)) {
            return films.get(id);
        }
        throw new NotFoundException("Фильм с id = " + id + " не найден");
    }

    @Override
    public Film create(Film film) {
        film.setId(getFilmId());
        films.put(film.getId(), film);
        log.info("Новый фильм: {} Общее количество фильмов в списке: {}", film, films.size());
        return film;
    }

    @Override
    public Film update(Film film) {
        if (films.containsKey(film.getId())) {
            log.info("Старые данные о фильме: {}", films.get(film.getId()));
            films.put(film.getId(), film);
            log.info("Новые данные о фильме: {}", films.get(film.getId()));
            return film;
        }
        throw new NotFoundException("Фильм с id = " + film.getId() + " не найден");
    }

    @Override
    public Film delete(Long id) {
        if (id == null) {
            throw new ValidationException("Укажите id фильма");
        }
        if (films.containsKey(id)) {
            return films.remove(id);
        }
        throw new NotFoundException("Фильм с id = " + id + " не найден");
    }

    public boolean containsFilm(long id) {
        return films.containsKey(id);
    }

    private long getFilmId() {
        return ++filmId;
    }

    private void resetFilmId() {
        filmId = 0;
    }
}
