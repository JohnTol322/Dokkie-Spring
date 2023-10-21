package com.dokkie.backend.event;

import com.dokkie.backend.participant.dto.ParticipantDTO;
import com.dokkie.backend.user.UserDTO;

import java.util.Date;
import java.util.List;

public record EventDTO(Long id, String description, Date createdOn, UserDTO user, List<ParticipantDTO> participants) { }
