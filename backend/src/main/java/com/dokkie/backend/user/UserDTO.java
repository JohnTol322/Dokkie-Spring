package com.dokkie.backend.user;

import com.dokkie.backend.event.EventDTO;
import com.dokkie.backend.participant.dto.ParticipantDTO;

import java.util.List;

public record UserDTO(Long id, String username, List<ParticipantDTO> participants, List<EventDTO> events) { }
