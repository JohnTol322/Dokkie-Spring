package com.dokkie.backend.event;

import com.dokkie.backend.user.User;
import com.dokkie.backend.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventFixture {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventFixture(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public void loadEvents() {
        this.createEvent("LAN party", 1L, Arrays.asList(1L, 3L, 4L));
        this.createEvent("Bowling party", 1L, Arrays.asList(2L, 3L, 5L));
        this.createEvent("Mario kart tournament", 2L, Arrays.asList(1L, 3L, 4L, 6L));
        this.createEvent("High-school reunion", 3L, Arrays.asList(1L, 3L, 4L));
        this.createEvent("Birthday party", 3L, Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L));
        this.createEvent("Paintball", 4L, Arrays.asList(1L, 3L, 4L, 6L));
        this.createEvent("Cinema", 4L, Arrays.asList(1L, 4L));
        this.createEvent("Halloween party", 5L, Arrays.asList(1L, 2L, 3L, 4L));
        this.createEvent("Football night", 5L, Arrays.asList(2L, 3L));
        this.createEvent("Saturday night in bar", 6L, Arrays.asList(1L, 2L, 5L));
    }

    private void createEvent(String description, Long creatorId, List<Long> participantIds) {
        Event event = new Event();
        event.setDescription(description);
        event.setActive(true);
        event.setCreatedOn(new Date());
        event.setUser(this.userRepository.findById(creatorId).orElse(null));
        List<Optional<User>> participants = participantIds.stream().map(this.userRepository::findById).toList();
        participants.forEach(participant -> participant.ifPresent(event::addParticipant));
        this.eventRepository.save(event);
    }
}
