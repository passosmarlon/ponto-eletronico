package com.ponto.eletronico.controller;

import com.ponto.eletronico.dto.PaymentDTO;
import com.ponto.eletronico.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PreAuthorize("hasHole('ADMIN')")
    @PostMapping
    public ResponseEntity<PaymentDTO> payment (@RequestBody PaymentDTO data) {
        var get = paymentService.salaryPayment(data);
        return ResponseEntity.ok(get);
    }
}
