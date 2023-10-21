package com.dokkie.user;

import com.dokkie.event.EventDTO;

import java.util.List;

public record UserDTO(Long id, String username, List<EventDTO> participations, List<EventDTO> events) { }
