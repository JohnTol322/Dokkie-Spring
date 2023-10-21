package com.dokkie.backend.user;

import org.springframework.stereotype.Component;

@Component
public class UserFixture {

    private final UserRepository userRepository;

    public UserFixture(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void loadUsers() {
        this.createUser("Joopie21");
        this.createUser("Jantje00");
        this.createUser("Jaap4");
        this.createUser("Jeff34");
        this.createUser("Bobbie111");
        this.createUser("Casey344");
    }

    private void createUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("Password21@");
        this.userRepository.save(user);
    }
}
