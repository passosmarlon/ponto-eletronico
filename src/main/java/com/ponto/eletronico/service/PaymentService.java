package com.ponto.eletronico.service;

import com.ponto.eletronico.dto.PaymentDTO;
import com.ponto.eletronico.entity.Employee;
import com.ponto.eletronico.entity.Payment;
import com.ponto.eletronico.entity.Status;
import com.ponto.eletronico.mapper.PaymentMapper;
import com.ponto.eletronico.repository.EmployeeRepository;
import com.ponto.eletronico.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public PaymentDTO salaryPayment(PaymentDTO data) {
        Employee employee = employeeRepository.findById(data.employeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employee.getHoursWorked() == null || employee.getHoursWorked().isZero()) {
            throw new RuntimeException("Employee has no hours to pay");
        }

        BigDecimal totalMinutes = BigDecimal.valueOf(employee.getHoursWorked().toMinutes());
        BigDecimal hoursDecimal = totalMinutes.divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

        BigDecimal salary = employee.getSalary();
        BigDecimal total = salary.multiply(hoursDecimal);

        Payment payment = new Payment();
        payment.setTotal(total);
        payment.setStatus(Status.PAID);
        payment.setDate(LocalDateTime.now());
        payment.setEmployee(employee);

        var saved = paymentRepository.save(payment);

        employee.setHoursWorked(Duration.ZERO);
        employeeRepository.save(employee);

        return PaymentMapper.toDTO(saved);
    }
}
