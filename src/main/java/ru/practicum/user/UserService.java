package ru.practicum.user;

import java.util.List;

public interface UserService {
    public UserDto addUser(UserDto userDtoRequest);

    public List<UserDto> getUsers();

    public UserDto updateUser(UserDto userDtoRequest, Integer userId);

    public UserDto getUserById(Integer userId);
}
