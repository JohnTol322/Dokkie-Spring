package com.dokkie.event;

import com.dokkie.payment.PaymentDTO;
import com.dokkie.user.UserDTO;

import java.util.Date;
import java.util.List;

public record EventDTO(Long id, String description, Date createdOn, List<UserDTO> participants, List<PaymentDTO> payments, UserDTO user) { }
