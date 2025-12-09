package com.ponto.eletronico.dto;

import com.ponto.eletronico.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDTO(Long id, Long employeeId, LocalDateTime date, Status status, BigDecimal total) {
}
