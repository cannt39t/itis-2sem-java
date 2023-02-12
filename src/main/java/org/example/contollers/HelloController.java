package org.example.contollers;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {

    private final UserRepository userRepository;

    @Autowired
    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with id " + id + " not found"));
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users/{name}/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@PathVariable String name, @PathVariable String email) {
        User user = new User(name, email);
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}/{name}/{email}")
    public User updateUser(@PathVariable Integer id, @PathVariable(required = false) Optional<String> name, @PathVariable(required = false) Optional<String> email) throws Exception {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with id " + id + " not found"));
        existingUser.setName(name.orElse(existingUser.getName()));
        existingUser.setEmail(email.orElse(existingUser.getEmail()));
        return userRepository.save(existingUser);
    }

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
}
