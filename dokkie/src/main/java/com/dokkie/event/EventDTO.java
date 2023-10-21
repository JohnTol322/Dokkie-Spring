package com.dokkie.event;

import com.dokkie.user.UserDTO;

import java.util.Date;
import java.util.List;

public record EventDTO(Long id, String description, Date createdOn, List<UserDTO> participants, UserDTO user) { }
