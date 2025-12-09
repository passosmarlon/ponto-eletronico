package com.ponto.eletronico.controller;

import com.ponto.eletronico.dto.PaymentDTO;
import com.ponto.eletronico.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> payment (@RequestBody PaymentDTO data) {
        var get = paymentService.salaryPayment(data);
        return ResponseEntity.ok(get);
    }
}
