package com.dokkie.payment;

import com.dokkie.event.Event;
import com.dokkie.event.EventRepository;
import com.dokkie.user.User;
import com.dokkie.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PaymentFixture {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public PaymentFixture(EventRepository eventRepository, UserRepository userRepository, PaymentRepository paymentRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    public void loadPayments() {
        this.paymentRepository.deleteAll();
        List<User> userList = new ArrayList<>();
        this.userRepository.findAll().forEach(userList::add);
        List<Event> eventList = new ArrayList<>();
        this.eventRepository.findAll().forEach(eventList::add);

        List<String> paymentDescriptions = Arrays.asList(
                "Bier", "Frisdrank", "Chips", "Snoep", "Tickets", "Taxi"
        );
        for (String paymentDescription : paymentDescriptions) {
            for (Event event : eventList) {
                Random random = new Random();
                this.createPayment(paymentDescription, random.nextDouble(150), this.getRandom(userList), event);
            }
        }
    }

    private void createPayment(String description, double amount, User user, Event event) {
        Payment payment = new Payment();
        payment.setDescription(description);
        payment.setAmount(amount);
        payment.setCreatedOn(new Date());

        payment.setUser(user);
        payment.setEvent(event);

        this.paymentRepository.save(payment);
    }

    private<T> T getRandom(List<T> pool) {
        return pool.get(new Random().nextInt(pool.size()));
    }
}
