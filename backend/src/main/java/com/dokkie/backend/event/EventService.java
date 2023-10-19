package com.dokkie.backend.event;

import com.dokkie.backend.user.User;
import com.dokkie.backend.user.UserRepository;
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

    public EventDTO createEvent(EventDTO eventDTO) {

        Event event = new Event();
        User user = this.userRepository.findById(eventDTO.user().id()).orElse(null);
        event.setDescription(eventDTO.description());
        event.setUser(user);

        return new EventDTO(this.eventRepository.save(event));
    }

    public List<EventDTO> listEvents() {
        Iterable<Event> events = this.eventRepository.findAll();
        List<EventDTO> result = new ArrayList<>();
        events.forEach(event -> result.add(new EventDTO(event)));

        return result;
    }


    public EventDTO getEvent(Long id) {
        Optional<Event> event = this.eventRepository.findById(id);
        return event.map(EventDTO::new).orElse(null);
    }
}
