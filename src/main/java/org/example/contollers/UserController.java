package org.example.contollers;

import org.example.dto.CreateUserRequestDto;
import org.example.dto.UserResponseDto;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with id " + id + " not found"));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(u -> UserResponseDto.builder()
                        .id(u.getId())
                        .name(u.getName())
                        .email(u.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

//    @PostMapping("/users/{name}/{email}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public User createUser(@PathVariable String name, @PathVariable String email) {
//        User user = new User(name, email);
//        return userRepository.save(user);
//    }

//    @PutMapping("/users/{id}/{name}/{email}")
//    public User updateUser(@PathVariable Integer id, @PathVariable(required = false) Optional<String> name, @PathVariable(required = false) Optional<String> email) throws Exception {
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new Exception("User with id " + id + " not found"));
//        existingUser.setName(name.orElse(existingUser.getName()));
//        existingUser.setEmail(email.orElse(existingUser.getEmail()));
//        return userRepository.save(existingUser);
//    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) throws Exception {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with id " + id + " not found"));
        userRepository.delete(existingUser);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello, %s!", name.orElse("Ivan"));
    }

    @PostMapping("/user")
    public UserResponseDto createUser(@Valid @RequestBody CreateUserRequestDto user) {
        return UserResponseDto.fromEntity(userRepository.save(
                User.builder()
                        .name(user.getName().trim())
                        .email(user.getEmail().trim())
                        .birth(user.getBirth())
                        .build()
        ));
    }
}
