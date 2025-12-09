package com.ponto.eletronico.repository;

import com.ponto.eletronico.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
