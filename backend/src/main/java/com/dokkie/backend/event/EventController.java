package com.dokkie.backend.event;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EventDTO createEventAction(@RequestBody EventDTO event) {
        return this.eventService.createEvent(event);
    }

    @GetMapping
    public List<EventDTO> listEventsAction() {
        return this.eventService.listEvents();
    }

    @GetMapping(path = "/{id}")
    public EventDTO getEventAction(@PathVariable("id") Long id) {
        return this.eventService.getEvent(id);
    }
}
