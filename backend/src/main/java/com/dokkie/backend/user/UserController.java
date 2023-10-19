package com.dokkie.backend.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUserAction(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @GetMapping
    public Iterable<User> listUsersAction() {
        return this.userService.listUsers();
    }

    @GetMapping(path = "/{id}")
    public User getUserAction(@PathVariable("id") Long id) {
        return this.userService.findUserById(id);
    }
}
