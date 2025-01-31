package user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    Integer userId;
    String userName;
    String login;
    String email;
}
