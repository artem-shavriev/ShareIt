package user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    UserMemoryRepository userRepository;

    @PostMapping
    public UserDto addUser(@RequestBody UserDto user) {
        return userRepository.addUser(user);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userRepository.getUsers();
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto user) {
        return userRepository.updateUser(user);
    }
}
