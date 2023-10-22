package com.dokkie.payment;

public record PaymentCreationDTO(String description, double amount, Long user, Long event) { }

