package com.ponto.eletronico.dto;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

public record EmployeeDTO(Long id, String name, String email, BigDecimal salary, Duration workedHours) {
}
