package com.dokkie.user;

import com.dokkie.event.EventDTO;
import com.dokkie.payment.PaymentDTO;

import java.util.List;

public record UserDTO(Long id, String username, List<EventDTO> participations, List<EventDTO> events, List<PaymentDTO> payments) { }
