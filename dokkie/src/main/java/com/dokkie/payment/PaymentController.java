package com.dokkie.payment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentDTO createPaymentAction(@RequestBody PaymentCreationDTO payment) {
        return this.paymentService.createPayment(payment);
    }

    @GetMapping
    public List<PaymentDTO> listPaymentsAction() {
        return this.paymentService.listPayments();
    }
}
