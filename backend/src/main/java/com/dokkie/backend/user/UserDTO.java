package com.dokkie.backend.user;

import com.dokkie.backend.event.EventDTO;

import java.util.List;

public record UserDTO(Long id, String username, List<EventDTO> participations, List<EventDTO> events) { }
