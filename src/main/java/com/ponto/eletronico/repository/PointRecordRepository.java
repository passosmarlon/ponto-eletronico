package com.ponto.eletronico.repository;

import com.ponto.eletronico.entity.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {

    Optional<PointRecord> findTopByEmployeeId_AndEndTimeIsNullOrderByDateDesc(Long employeeId);
}
