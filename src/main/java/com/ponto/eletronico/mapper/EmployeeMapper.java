package com.ponto.eletronico.mapper;

import com.ponto.eletronico.dto.EmployeeDTO;
import com.ponto.eletronico.entity.Employee;
import com.ponto.eletronico.entity.PointRecord;

public class EmployeeMapper {

    public static EmployeeDTO toDTO (Employee entity) {
        if (entity == null) {
            return null;
        }
        return new EmployeeDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getSalary(),
                entity.getHoursWorked()
        );
    }

    public static Employee toEntity (EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Employee(
                dto.id(),
                dto.name(),
                dto.email(),
                dto.salary(),
                dto.workedHours()
        );

    }
}
