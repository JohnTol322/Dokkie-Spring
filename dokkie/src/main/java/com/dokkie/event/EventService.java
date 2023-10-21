package com.dokkie.event;

import com.dokkie.user.User;
import com.dokkie.user.UserDTO;
import com.dokkie.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public EventDTO createEvent(EventCreationDTO eventDTO) {
        Event event = new Event();
        User user = this.userRepository.findById(eventDTO.user()).orElse(null);
        eventDTO.participants().forEach(id -> {
            Optional<User> result = this.userRepository.findById(id);
            result.ifPresent(event::addParticipant);
        });
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
        List<UserDTO> participantDTOs = event.getParticipants().stream()
                .map(participant -> new UserDTO(participant.getId(), participant.getUsername(), null, null))
                .toList();
        UserDTO userDTO = new UserDTO(event.getUser().getId(), event.getUser().getUsername(), null, null);

        return new EventDTO(event.getId(), event.getDescription(), event.getCreatedOn(), participantDTOs, userDTO);
    }
}
