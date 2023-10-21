package com.dokkie.backend.participant;

import com.dokkie.backend.participant.dto.ParticipantDTO;
import com.dokkie.backend.participant.dto.ParticipantRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/participant")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping
    public ParticipantDTO createParticipantAction(@RequestBody ParticipantRequestDTO participant) {
        return this.participantService.createParticipant(participant);
    }

    @GetMapping(path = "{id}/event")
    public List<ParticipantDTO> findParticipantsByEvent(@PathVariable("id") Long id) {
        return this.participantService.findParticipantsByEvent(id);
    }
}
