package com.dokkie.user;

import com.dokkie.event.Event;
import com.dokkie.payment.Payment;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Event> events;

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "participants")
    private List<Event> participations;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private List<Payment> payments;

    private String username;
    private String password;

    public User() {
        this.events = new ArrayList<>();
        this.participations = new ArrayList<>();
    }

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
    public List<Event> getParticipations() {
        return participations;
    }

    public void addParticipations(Event participation) {
        if (!this.participations.contains(participation)) {
            this.participations.add(participation);
        }
    }

    public void removePayment(Payment payment) {
        this.payments.remove(payment);
    }
    public List<Payment> getPayments() {
        return this.payments;
    }

    public void addPayment(Payment payment) {
        if (!this.payments.contains(payment)) {
            this.payments.add(payment);
        }
    }

    public void removeParticipation(Event participation) {
        this.participations.remove(participation);
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
