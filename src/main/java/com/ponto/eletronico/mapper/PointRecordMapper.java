package com.ponto.eletronico.mapper;

import com.ponto.eletronico.dto.PointRecordDTO;
import com.ponto.eletronico.entity.Employee;
import com.ponto.eletronico.entity.PointRecord;

public class PointRecordMapper {

    public static PointRecordDTO toDTO (PointRecord entity) {
        if (entity == null) {
            return null;
        }
        Long employeeId = entity.getEmployee() != null ? entity.getEmployee().getId() : null;

        return new PointRecordDTO(
                entity.getId(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getDate(),
                employeeId
        );
    }

    public static PointRecord toEntity (PointRecordDTO dto, Employee employee) {
        if (dto == null) {
            return null;
        }

        PointRecord entity = new PointRecord();
        entity.setId(dto.id());
        entity.setEmployee(employee);
        entity.setStartTime(dto.startTime());
        entity.setEndTime(dto.endTime());
        entity.setDate(dto.date());
        return entity;
    }
}


