package com.dokkie.payment;

import com.dokkie.event.EventDTO;
import com.dokkie.user.UserDTO;

public record PaymentDTO(Long id, String description, double amount, UserDTO user, EventDTO event) { }
