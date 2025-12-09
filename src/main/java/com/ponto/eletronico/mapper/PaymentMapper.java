package com.ponto.eletronico.mapper;

import com.ponto.eletronico.dto.PaymentDTO;
import com.ponto.eletronico.entity.Employee;
import com.ponto.eletronico.entity.Payment;

public class PaymentMapper {

    public static PaymentDTO toDTO(Payment entity) {

        if (entity == null) {
            return null;
        }
        Long employeeId = entity.getEmployee() != null ? entity.getEmployee().getId() : null;
        return new PaymentDTO(
                entity.getId(),
                employeeId,
                entity.getDate(),
                entity.getStatus(),
                entity.getTotal()
        );
    }

    public static Payment toEntity(PaymentDTO dto, Employee employee) {

        if (dto == null) {
            return null;
        }
        Payment entity = new Payment();
        entity.setId(dto.id());
        entity.setEmployee(employee);
        entity.setDate(dto.date());
        entity.setStatus(dto.status());
        entity.setTotal(dto.total());
        return entity;

    }

}
