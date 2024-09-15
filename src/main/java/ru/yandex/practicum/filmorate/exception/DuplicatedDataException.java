package ru.yandex.practicum.filmorate.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DuplicatedDataException extends RuntimeException {
    public DuplicatedDataException(String message) {
        super(message);
        log.warn(message);
    }
}
