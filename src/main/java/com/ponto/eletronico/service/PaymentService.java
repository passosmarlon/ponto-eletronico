package com.ponto.eletronico.service;

import com.ponto.eletronico.dto.PaymentDTO;
import com.ponto.eletronico.entity.Employee;
import com.ponto.eletronico.entity.Payment;
import com.ponto.eletronico.entity.Status;
import com.ponto.eletronico.mapper.PaymentMapper;
import com.ponto.eletronico.repository.EmployeeRepository;
import com.ponto.eletronico.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final EmployeeRepository employeeRepository;

    public PaymentService(PaymentRepository paymentRepository, EmployeeRepository employeeRepository) {
        this.paymentRepository = paymentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public PaymentDTO salaryPayment(PaymentDTO data) {
        Payment payment = new Payment();
        Employee employee = employeeRepository.findById(data.employeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employee.getHoursWorked() == null) {
            return null;
        }
        Long hours = employee.getHoursWorked().toMinutes();
        hours /= 60;
        BigDecimal salary = employee.getSalary();

        BigDecimal total = salary.multiply(BigDecimal.valueOf(hours));

        payment.setTotal(total);
        payment.setStatus(Status.valueOf("PAID"));
        payment.setDate(LocalDateTime.now());
        payment.setEmployee(employee);

        var saved = paymentRepository.save(payment);
        employee.setHoursWorked(Duration.ZERO);
        return PaymentMapper.toDTO(saved);

    }
}
