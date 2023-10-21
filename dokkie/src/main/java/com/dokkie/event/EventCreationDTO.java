package com.dokkie.event;

import java.util.List;

public record EventCreationDTO(String description, Long user, List<Long> participants) { }
