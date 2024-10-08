package ru.yandex.practicum.filmorate.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedDataException extends RuntimeException {
    public DuplicatedDataException(String message) {
        super(message);
        log.warn(message);
    }
}
