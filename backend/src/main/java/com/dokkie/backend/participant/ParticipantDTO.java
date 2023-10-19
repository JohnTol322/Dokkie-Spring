package com.dokkie.backend.participant;

import com.dokkie.backend.event.EventDTO;
import com.dokkie.backend.user.UserDTO;

public record ParticipantDTO(Long id, UserDTO user, EventDTO event) {
    public ParticipantDTO(Participant participant) {
        this(participant.getId(), new UserDTO(participant.getUser()), new EventDTO(participant.getEvent()));
    }
}
