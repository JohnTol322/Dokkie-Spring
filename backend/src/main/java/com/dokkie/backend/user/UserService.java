package com.dokkie.backend.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    public User findUserById(Long id) {
        Optional<User> value = this.userRepository.findById(id);

        return value.orElse(null);
    }

    public Iterable<User> listUsers() {
        return this.userRepository.findAll();
    }
}
