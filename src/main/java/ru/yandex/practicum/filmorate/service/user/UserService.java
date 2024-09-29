package ru.yandex.practicum.filmorate.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.DuplicatedDataException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {
    private UserStorage userStorage;

    public Collection<User> getFriends(Long id) {
        User user = userStorage.findById(id);
        return user.getFriends().stream()
                .map(friendId -> userStorage.findById(friendId))
                .toList();
    }

    public User addFriend(Long userId, Long friendId) {
        if (Objects.equals(userId, friendId)) {
            throw new DuplicatedDataException("Id пользователей не должны совпадать");
        }
        User user = userStorage.findById(userId);
        User friend = userStorage.findById(friendId);
        user.getFriends().add(friend.getId());
        friend.getFriends().add(user.getId());
        return user;
    }

    public User deleteFriend(Long userId, Long friendId) {
        if (Objects.equals(userId, friendId)) {
            throw new DuplicatedDataException("Id пользователей не должны совпадать");
        }
        User user = userStorage.findById(userId);
        User friend = userStorage.findById(friendId);
        if (user.getFriends().contains(friend.getId())) {
            user.getFriends().remove(friend.getId());
            friend.getFriends().remove(user.getId());
            return user;
        }
        throw new NotFoundException("Друг с id = " + friend.getId() + " не найден");
    }

    public Collection<User> getCommonFriends(Long firstUserId, Long secondUserId) {
        if (Objects.equals(firstUserId, secondUserId)) {
            throw new DuplicatedDataException("Id пользователей не должны совпадать");
        }
        User user1 = userStorage.findById(firstUserId);
        User user2 = userStorage.findById(secondUserId);
        return user1.getFriends().stream()
                .filter(id -> user2.getFriends().contains(id))
                .map(id -> userStorage.findById(id))
                .toList();
    }
}
