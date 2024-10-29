package ru.yandex.practicum.filmorate.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@Repository
public class FilmDbStorage extends BaseRepository<Film> implements FilmStorage {
    @Autowired
    private MpaDbStorage mpaDbStorage;
    @Autowired
    private GenreDbStorage genreDbStorage;
    private static final String INSERT_QUERY = "INSERT INTO films (name, description, release_date, duration, rating_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    public FilmDbStorage(JdbcTemplate jdbc, RowMapper<Film> mapper) {
        super(jdbc, mapper, Film.class);
    }

    @Override
    public Collection<Film> findAll() {
//        return films.values();
        return null;
    }

    @Override
    public Film findById(Long id) {
//        findOne()
        return null;
    }

    @Override
    public Film create(Film film) {
        Mpa mpa = mpaDbStorage.findById(film.getMpa().getId())
                .orElseThrow(() -> new ValidationException("Указанный Mpa не существует."));
        List<Genre> genres = genreDbStorage.findMany(film.getGenres());
        long id = insert(
                INSERT_QUERY,
                film.getName(),
                film.getDescription(),
                Timestamp.valueOf(film.getReleaseDate().atStartOfDay()),
                film.getDuration(),
                film.getMpa().getId()
        );
        film.setId(id);
        film.setMpa(mpa);
        film.setGenres(new LinkedHashSet<>(genres));
        genreDbStorage.setFilmGenres(film.getId(), film.getGenres());
        return film;
    }

    @Override
    public Film update(Film film) {
//        log.info("Старые данные о фильме: {}", films.get(film.getId()));
//        Film newFilm = films.computeIfPresent(film.getId(), (key, value) -> value = film);
//        if (newFilm != null) {
//            log.info("Новые данные о фильме: {}", films.get(film.getId()));
//            return newFilm;
//        }
        throw new NotFoundException("Фильм с id = " + film.getId() + " не найден");
    }

    @Override
    public Film delete(Long id) {
//        return Optional.ofNullable(films.remove(id))
//                .orElseThrow(() -> new NotFoundException("Фильм с id = " + id + " не найден"));
        return null;
    }
}
