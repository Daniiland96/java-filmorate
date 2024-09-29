package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.DuplicatedDataException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private Map<Long, User> users = new HashMap<>();
    private long userId = 0;

    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    @Override
    public User findById(Long id) {
        if (users.containsKey(id)) {
            return users.get(id);
        }
        throw new NotFoundException("Пользователь с id = " + id + " не найден");
    }

    @Override
    public User create(User user) {
        isEmailExists(user);
        user.setId(getUserId());
        users.put(user.getId(), user);
        log.info("Новый пользователь: {} Общее количество пользователей: {}", user, users.size());
        return user;
    }

    @Override
    public User update(User user) {
        if (users.containsKey(user.getId())) {
            isEmailExists(user);
            log.info("Старые данные о пользователе: {}", users.get(user.getId()));
            users.put(user.getId(), user);
            log.info("Новые данные о пользователе: {}", users.get(user.getId()));
            return user;
        }
        throw new NotFoundException("Пользователь с id = " + user.getId() + " не найден");
    }

    @Override
    public User delete(Long id) {
        if (users.containsKey(id)) {
            return users.remove(id);
        }
        throw new NotFoundException("Пользователь с id = " + id + " не найден");
    }

    private long getUserId() {
        return ++userId;
    }

    private void resetUserId() {
        userId = 0;
    }

    private void isEmailExists(User newUser) {
        boolean result = users.values()
                .stream()
                .filter(user -> !(user.getId() == newUser.getId()))
                .map(User::getEmail)
                .anyMatch(email -> email.equals(newUser.getEmail()));
        if (result) {
            String message = "Этот имейл уже используется";
            log.warn(message);
            throw new DuplicatedDataException(message);
        }
    }
}
