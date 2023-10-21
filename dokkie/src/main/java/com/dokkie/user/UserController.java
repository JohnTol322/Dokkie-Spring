package com.dokkie.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUserAction(@RequestBody UserCreationDTO user) {
        return this.userService.createUser(user);
    }

    @GetMapping
    public List<UserDTO> listUsersAction() {
        return this.userService.listUsers();
    }

    @GetMapping(path = "/{id}")
    public UserDTO getUserAction(@PathVariable("id") Long id) {
        return this.userService.findUserById(id);
    }
}
