package com.dokkie.backend.event;

import com.dokkie.backend.participant.dto.ParticipantDTO;
import com.dokkie.backend.user.User;
import com.dokkie.backend.user.UserDTO;
import com.dokkie.backend.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public EventDTO createEvent(EventDTO eventDTO) {

        Event event = new Event();
        User user = this.userRepository.findById(eventDTO.user().id()).orElse(null);
        event.setDescription(eventDTO.description());
        event.setUser(user);

        return convertToDTO(this.eventRepository.save(event));
    }

    public List<EventDTO> listEvents() {
        Iterable<Event> events = this.eventRepository.findAll();
        List<EventDTO> result = new ArrayList<>();
        events.forEach(event -> result.add(convertToDTO(event)));

        return result;
    }


    public EventDTO getEvent(Long id) {
        Optional<Event> event = this.eventRepository.findById(id);
        return event.map(EventService::convertToDTO).orElse(null);
    }

    public static EventDTO convertToDTO(Event event) {
        UserDTO userDTO = new UserDTO(event.getUser().getId(), event.getUser().getUsername(), null, null);
        List<ParticipantDTO> participantDTOs = event.getParticipants().stream()
                .map(participant -> new ParticipantDTO(participant.getId(), new UserDTO(participant.getUser().getId(), participant.getUser().getUsername(), null, null), null))
                .toList();

        return new EventDTO(event.getId(), event.getDescription(), event.getCreatedOn(), userDTO, participantDTOs);
    }
}
