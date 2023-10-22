package com.dokkie.event;

import com.dokkie.user.User;
import com.dokkie.user.UserRepository;
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
        this.eventRepository.deleteAll();

        Iterable<User> users = this.userRepository.findAll();

        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);

        this.createEvent("LAN party", this.getRandomUser(userList), this.getRandomUserList(userList, 4));
        this.createEvent("Bowling party", this.getRandomUser(userList), this.getRandomUserList(userList, 3));
        this.createEvent("Mario kart tournament", this.getRandomUser(userList), this.getRandomUserList(userList, 4));
        this.createEvent("High-school reunion", this.getRandomUser(userList), this.getRandomUserList(userList, 3));
        this.createEvent("Birthday party", this.getRandomUser(userList), this.getRandomUserList(userList, 6));
        this.createEvent("Paintball", this.getRandomUser(userList), this.getRandomUserList(userList, 2));
        this.createEvent("Cinema", this.getRandomUser(userList), this.getRandomUserList(userList, 2));
        this.createEvent("Halloween party", this.getRandomUser(userList), this.getRandomUserList(userList, 5));
        this.createEvent("Football night", this.getRandomUser(userList), this.getRandomUserList(userList, 3));
        this.createEvent("Saturday night in bar", this.getRandomUser(userList), this.getRandomUserList(userList, 4));
    }

    private void createEvent(String description, User creator, List<User> participants) {
        Event event = new Event();
        event.setDescription(description);
        event.setActive(true);
        event.setCreatedOn(new Date());
        event.setUser(creator);
        participants.forEach(event::addParticipant);
        this.eventRepository.save(event);
    }

    private User getRandomUser(List<User> userPool) {
        return userPool.get(new Random().nextInt(userPool.size()));
    }

    private List<User> getRandomUserList(List<User> userPool, int count) {
        if (count > userPool.size()) {
            count = userPool.size();
        }

        List<User> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(userPool.get(new Random().nextInt(count)));
        }

        return result;
    }
}
