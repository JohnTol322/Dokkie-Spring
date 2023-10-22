package com.dokkie.payment;

import com.dokkie.event.Event;
import com.dokkie.event.EventDTO;
import com.dokkie.event.EventRepository;
import com.dokkie.user.User;
import com.dokkie.user.UserDTO;
import com.dokkie.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            UserRepository userRepository,
            EventRepository eventRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public PaymentDTO createPayment(PaymentCreationDTO paymentDTO) {
        Payment payment = new Payment();
        User user = this.userRepository.findById(paymentDTO.user()).orElse(null);
        Event event = this.eventRepository.findById(paymentDTO.event()).orElse(null);

        payment.setDescription(paymentDTO.description());
        payment.setAmount(paymentDTO.amount());
        payment.setCreatedOn(new Date());
        payment.setUser(user);
        payment.setEvent(event);

        return convertToDTO(this.paymentRepository.save(payment));
    }

    public List<PaymentDTO> listPayments() {
        Iterable<Payment> payments = this.paymentRepository.findAll();
        List<PaymentDTO> result = new ArrayList<>();
        payments.forEach(payment -> result.add(convertToDTO(payment)));

        return result;
    }

    public static PaymentDTO convertToDTO(Payment payment) {
        UserDTO userDTO = new UserDTO(payment.getUser().getId(), payment.getUser().getUsername(), null, null);
        EventDTO eventDTO = new EventDTO(payment.getEvent().getId(), payment.getEvent().getDescription(), payment.getEvent().getCreatedOn(), null, null);

        return new PaymentDTO(payment.getId(), payment.getDescription(), payment.getAmount(), userDTO, eventDTO);
    }
}
