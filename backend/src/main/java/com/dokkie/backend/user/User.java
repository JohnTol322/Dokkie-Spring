package com.dokkie.backend.user;

import com.dokkie.backend.event.Event;
import com.dokkie.backend.participant.Participant;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Event> events;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Participant> participants;

    private String username;
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        if (!this.events.contains(event)) {
            this.events.add(event);
        }
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }
    public List<Participant> getParticipants() {
        return participants;
    }

    public void addParticipant(Participant participant) {
        if (!this.participants.contains(participant)) {
            this.participants.add(participant);
        }
    }

    public void removeParticipant(Participant participant) {
        this.participants.remove(participant);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
