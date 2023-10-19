package com.dokkie.backend.event;

import com.dokkie.backend.user.UserDTO;

import java.util.Date;

public record EventDTO(Long id, String description, Date createdOn, UserDTO user) {
    public EventDTO(Event event) {
        this(event.getId(), event.getDescription(), event.getCreatedOn(), new UserDTO(event.getUser()));
    }
}
