package user;

public class UserMapper {
    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();

        user.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setLogin(user.getLogin());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public User mapToUser(UserDto userDto) {
        User user = new User();

        user.setUserId(user.getUserId());
        user.setUserName(userDto.getUserName());
        user.setLogin(userDto.getLogin());
        user.setEmail(userDto.getEmail());

        return user;
    }
}
