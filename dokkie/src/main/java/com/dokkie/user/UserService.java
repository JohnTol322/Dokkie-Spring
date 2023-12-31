package com.dokkie.user;

import com.dokkie.event.EventDTO;
import com.dokkie.payment.Payment;
import com.dokkie.payment.PaymentDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreationDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());

        return this.userRepository.save(user);
    }

    public UserDTO findUserById(Long id) {
        Optional<User> users = this.userRepository.findById(id);

        return users.map(UserService::convertToDTO).orElse(null);
    }

    public List<UserDTO> listUsers() {
        Iterable<User> users = this.userRepository.findAll();
        List<UserDTO> result = new ArrayList<>();
        users.forEach(user -> result.add(convertToDTO(user)));

        return result;
    }

    public static UserDTO convertToDTO(User user) {
        List<EventDTO> participationDTOs = user.getParticipations().stream()
                .map(participation -> new EventDTO(participation.getId(), participation.getDescription(), participation.getCreatedOn(), null, null, null))
                .toList();
        List<EventDTO> eventDTOs = user.getEvents().stream()
                .map(event -> new EventDTO(event.getId(), event.getDescription(), event.getCreatedOn(), null, null, null))
                .toList();
        List<PaymentDTO> paymentDTOS = user.getPayments().stream()
                .map(payment -> new PaymentDTO(payment.getId(), payment.getDescription(), payment.getAmount(), payment.getCreatedOn(), null, null))
                .toList();

        return new UserDTO(user.getId(), user.getUsername(), participationDTOs, eventDTOs, paymentDTOS);
    }
}
