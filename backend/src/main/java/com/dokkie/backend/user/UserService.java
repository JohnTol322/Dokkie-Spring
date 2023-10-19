package com.dokkie.backend.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public UserDTO findUserById(Long id) {
        Optional<User> users = this.userRepository.findById(id);

        return users.map(UserDTO::new).orElse(null);
    }

    public List<UserDTO> listUsers() {
        Iterable<User> users = this.userRepository.findAll();
        List<UserDTO> result = new ArrayList<>();
        users.forEach(user -> result.add(new UserDTO(user)));

        return result;
    }
}
