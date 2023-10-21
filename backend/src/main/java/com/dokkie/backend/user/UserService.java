package com.dokkie.backend.user;

import com.dokkie.backend.event.EventDTO;
import com.dokkie.backend.participant.Participant;
import com.dokkie.backend.participant.dto.ParticipantDTO;
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

    public User createUser(User user) {
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
        List<ParticipantDTO> participantDTOs = user.getParticipants().stream()
                .map(participant -> new ParticipantDTO(participant.getId(), null, new EventDTO(
                        participant.getEvent().getId(),
                        participant.getEvent().getDescription(),
                        participant.getEvent().getCreatedOn(),
                        null,
                        null
                )
                ))
                .toList();

        List<EventDTO> eventDTOs = user.getEvents().stream()
                .map(event -> new EventDTO(event.getId(), event.getDescription(), event.getCreatedOn(), null, null))
                .toList();

        return new UserDTO(user.getId(), user.getUsername(), participantDTOs, eventDTOs);
    }
}
