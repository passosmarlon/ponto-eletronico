package com.ponto.eletronico.dto;

import com.ponto.eletronico.entity.Employee;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public record PointRecordDTO(Long id, LocalTime startTime, LocalTime endTime, LocalDate date, Long employeeId) {
}
