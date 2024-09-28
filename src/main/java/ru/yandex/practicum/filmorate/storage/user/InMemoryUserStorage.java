package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage{
    @Override
    public Collection<User> findAll() {
        return List.of();
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User newUser) {
        return null;
    }

    @Override
    public User delete(Long id) {
        return null;
    }
}
