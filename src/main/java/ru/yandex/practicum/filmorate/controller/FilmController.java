package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private Map<Long, Film> films = new HashMap<>();


    @GetMapping
    public Collection<Film> findAll() {
        return films.values();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        try {
            validate(film);
            film.setId(getNextId());
            films.put(film.getId(), film);
            log.info("Новый фильм: {} Общее количество фильмов в списке: {}", film, films.size());
            return film;
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            throw e;
        }
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film newFilm) {
        try {
            validate(newFilm);
            if (films.containsKey(newFilm.getId())) {
                Film oldFilm = films.get(newFilm.getId());
                log.info("Старые данные о фильме: {}", oldFilm);
                oldFilm = newFilm;
                log.info("Новые данные о фильме: {}", oldFilm);
                return oldFilm;
            }
            throw new NotFoundException("Фильм с id = " + newFilm.getId() + " не найден");
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            throw e;
        }
    }

    private void validate(Film film) {
//        if (film.getName() == null || film.getName().isBlank()) {
//            throw new ValidationException("Название не может быть пустым");
////        }
//        if (film.getDescription().length() > 200) {
//            throw new ValidationException("Максимальная длина описания — 200 символов");
//        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Дата релиза — не раньше 28 декабря 1895 года");
        }
//        if (film.getDuration() < 0) {
//            throw new ValidationException("Продолжительность фильма должна быть положительным числом");
//        }
    }

    private long getNextId() {
        long currentMaxId = films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
