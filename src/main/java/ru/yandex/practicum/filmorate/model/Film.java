package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.yandex.practicum.filmorate.validator.FilmReleaseDateConstraint;

import java.time.LocalDate;
import java.util.*;

/**
 * Film.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private long id;
    @NotBlank(message = "Название не может быть пустым")
    private String name;
    @Size(max = 200, message = "Максимальная длина описания — 200 символов")
    private String description;
    @FilmReleaseDateConstraint
    private LocalDate releaseDate;
    @Positive(message = "Продолжительность фильма должна быть положительным числом")
    private long duration;
    private Mpa mpa;
    private Set<Genre> genres = new LinkedHashSet<>();
    private Collection<Long> usersLikes = new ArrayList<>();

//    public Film(long id, String name, String description, LocalDate releaseDate, long duration, Mpa mpa
//            , Set<Genre> genres) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.releaseDate = releaseDate;
//        this.duration = duration;
//        this.mpa = mpa;
//        this.genres = genres;
//    }
//
//    public Film(long id, String name, String description, LocalDate releaseDate, long duration, Mpa mpa) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.releaseDate = releaseDate;
//        this.duration = duration;
//        this.mpa = mpa;
//    }
}
