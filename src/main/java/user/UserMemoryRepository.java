package user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Repository
public class UserMemoryRepository {
    private final UserMapper userMapper;
    Map<Integer, User> usersMap = new HashMap<>();

    public UserDto addUser(UserDto userDtoRequest) {
        User user = userMapper.mapToUser(userDtoRequest);
        usersMap.put(user.getUserId(), user);
        log.info("Пользователь с id " + user.getUserId() + "добавлен");
        return userDtoRequest;
    }

    public List<UserDto> getUsers() {
        List<UserDto> UserDtoList = usersMap.values().stream().map(user -> userMapper.mapToUserDto(user)).toList();
        log.info("Все пользователи получены.");
        return UserDtoList;
    }

    public UserDto updateUser(UserDto userDtoRequest) {
        if (!usersMap.containsKey(userDtoRequest.getUserId())) {
            log.error("пользователь с id {} не найден.", userDtoRequest.getUserId());
            throw new RuntimeException("Пользовтеь с id " + userDtoRequest.getUserId() + "не найден.");
        }

        User user = userMapper.mapToUser(userDtoRequest);
        usersMap.put(user.getUserId(), user);
        log.info("Пользователь с id {} обновлен", user.getUserId());

        return userDtoRequest;
    }
}
