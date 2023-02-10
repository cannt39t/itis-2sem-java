package org.example.contollers;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {

    private final UserRepository userRepository;

    @Autowired
    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/user/{id}")
    public Iterable<User> findUser(@PathVariable(required = false) Optional<Integer> id) {
        if (id.isPresent()) {
            return userRepository.findAllById(List.of(id.get()));
        } else {
            return userRepository.findAll();
        }
    }

    @GetMapping("/user/create/{name}/{email}")
    public void createUser(@PathVariable String name, @PathVariable(required = true) String email) {
        User user = new User(name, email);
        userRepository.save(user);
    }

    @GetMapping("/user/delete/{id}/")
    public void delete(@PathVariable Integer id) {
        User user = new User(id);
        userRepository.delete(user);
    }

    @GetMapping("/user/update/{id}/{name}/{email}")
    public void updateUser(@PathVariable Integer id, @PathVariable(required = false) Optional<String> name, @PathVariable(required = false) Optional<String> email) {
        User user = new User(id, name.orElse(""), email.orElse(""));
        userRepository.save(user);
    }


    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello, %s!", name.orElse("Ivan"));
    }
}
