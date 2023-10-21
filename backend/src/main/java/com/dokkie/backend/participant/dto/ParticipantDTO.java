package com.dokkie.backend.participant.dto;

import com.dokkie.backend.event.EventDTO;
import com.dokkie.backend.user.UserDTO;

public record ParticipantDTO(Long id, UserDTO user, EventDTO event) { }
