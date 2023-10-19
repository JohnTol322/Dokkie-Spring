package com.dokkie.backend.user;

public record UserDTO(Long id, String username) {
    public UserDTO(User user) {
        this(user.getId(), user.getUsername());
    }
}
