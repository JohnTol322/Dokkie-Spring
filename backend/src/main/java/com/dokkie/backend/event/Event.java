package com.dokkie.backend.event;

import com.dokkie.backend.participant.Participant;
import com.dokkie.backend.payment.Payment;
import com.dokkie.backend.user.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "event_id")
    private List<Participant> participants;

    @OneToMany
    @JoinColumn(name = "event_id")
    private List<Payment> payments;

    private String description;
    private Date createdOn = new Date();
    private boolean active = true;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Payment> getPayments() {
        return payments;
    }

    public void addPayment(Payment payment) {
        if (!this.payments.contains(payment)) {
            this.payments.add(payment);
        }
    }

    public void removePayment(Payment payment) {
        this.payments.remove(payment);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
