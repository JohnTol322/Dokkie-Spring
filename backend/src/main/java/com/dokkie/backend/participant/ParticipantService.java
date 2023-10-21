package com.dokkie.backend.participant;

import com.dokkie.backend.event.Event;
import com.dokkie.backend.event.EventDTO;
import com.dokkie.backend.event.EventRepository;
import com.dokkie.backend.participant.dto.ParticipantDTO;
import com.dokkie.backend.participant.dto.ParticipantRequestDTO;
import com.dokkie.backend.user.User;
import com.dokkie.backend.user.UserDTO;
import com.dokkie.backend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public ParticipantService(
            ParticipantRepository participantRepository,
            UserRepository userRepository,
            EventRepository eventRepository
    ) {
        this.participantRepository = participantRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public ParticipantDTO createParticipant(ParticipantRequestDTO participantDTO) {
        Participant participant = new Participant();
        User user = this.userRepository.findById(participantDTO.user()).orElse(null);
        Event event = this.eventRepository.findById(participantDTO.event()).orElse(null);
        participant.setUser(user);
        participant.setEvent(event);

        return convertToDTO(this.participantRepository.save(participant));
    }

    public List<ParticipantDTO> findParticipantsByEvent(Long eventId) {
        Event event = this.eventRepository.findById(eventId).orElse(null);
        List<Participant> participants = this.participantRepository.findByEvent(event);
        List<ParticipantDTO> result = new ArrayList<>();
        participants.forEach(value -> result.add(convertToDTO(value)));

        return result;
    }

    public static ParticipantDTO convertToDTO(Participant participant) {
        UserDTO userDTO = new UserDTO(participant.getUser().getId(), participant.getUser().getUsername(), null, null);
        EventDTO eventDTO = new EventDTO(participant.getEvent().getId(), participant.getEvent().getDescription(), participant.getEvent().getCreatedOn(), null, null);

        return new ParticipantDTO(participant.getId(), userDTO, eventDTO);
    }
}
