package ru.practicum.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();

        user.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLogin(user.getLogin());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public User mapToUser(UserDto userDto) {
        User user = new User();

        user.setId(user.getId());
        user.setName(userDto.getName());
        user.setLogin(userDto.getLogin());
        user.setEmail(userDto.getEmail());

        return user;
    }
}
