package com.dokkie.pages;

import com.dokkie.event.Event;
import com.dokkie.event.EventDTO;
import com.dokkie.event.EventService;
import com.dokkie.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PageController {

    private final EventService eventService;
    private final UserService userService;

    public PageController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping(path = "/events")
    public String listEvents(Model model) {
        List<EventDTO> events = this.eventService.listEvents();

        model.addAttribute("events", events);
        return "event/list_events";
    }

    @GetMapping(path = "/event/{id}/details")
    public String showEventDetails(Model model, @PathVariable("id") Long id) {
        EventDTO event = this.eventService.getEvent(id);

        model.addAttribute("event", event);
        return "event/event_details";
    }
}
