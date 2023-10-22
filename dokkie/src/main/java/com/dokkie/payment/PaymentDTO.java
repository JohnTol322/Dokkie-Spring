package com.dokkie.payment;

import com.dokkie.event.EventDTO;
import com.dokkie.user.UserDTO;

import java.util.Date;

public record PaymentDTO(Long id, String description, double amount, Date createdOn, UserDTO user, EventDTO event) { }
