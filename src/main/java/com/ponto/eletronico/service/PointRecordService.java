package com.ponto.eletronico.service;

import com.ponto.eletronico.dto.PointRecordDTO;
import com.ponto.eletronico.entity.Employee;
import com.ponto.eletronico.entity.PointRecord;
import com.ponto.eletronico.mapper.PointRecordMapper;
import com.ponto.eletronico.repository.EmployeeRepository;
import com.ponto.eletronico.repository.PointRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class PointRecordService {

    private final PointRecordRepository pointRecordRepository;
    private final EmployeeRepository employeeRepository;
    private final PointCalculateService service;

    public PointRecordService(PointRecordRepository pointRecordRepository, EmployeeRepository employeeRepository, PointCalculateService service) {
        this.pointRecordRepository = pointRecordRepository;
        this.employeeRepository = employeeRepository;
        this.service = service;
    }

    @Transactional
    public PointRecordDTO savePointRecord(PointRecordDTO data) {
        Employee employee = employeeRepository.findById(data.employeeId())
                .orElseThrow(()-> new RuntimeException("Employee não encontrado"));

        PointRecord entity = PointRecordMapper.toEntity(data, employee);

        entity.setStartTime(LocalTime.now());
        entity.setDate(LocalDate.now());

        PointRecordMapper.toDTO(entity);

        PointRecord saved = pointRecordRepository.save(entity);

        return PointRecordMapper.toDTO(saved);
    }

    @Transactional
    public PointRecordDTO saveEndPointRecord(PointRecordDTO data, Long employeeId) {
        // Filtra o ultimo registro de ponto do Funcionário
        PointRecord pointRecord=pointRecordRepository.findTopByEmployeeId_AndEndTimeIsNullOrderByDateDesc(employeeId)
                .orElseThrow(() -> new RuntimeException("Nenhum registro de entrada encontrado"));

        if (data.startTime() != null) {
            pointRecord.setStartTime(data.startTime());
        }
        if (data.date() != null) {
            pointRecord.setDate(data.date());
        }
        pointRecord.setEndTime(LocalTime.now());

        Duration workedTime = service.calculateHours(pointRecord);

        Employee entity = pointRecord.getEmployee();

        Duration currentWorkedHours = entity.getHoursWorked();
        Duration newTotal = currentWorkedHours.plus(workedTime);

        entity.setHoursWorked(newTotal);
        employeeRepository.save(entity);

       PointRecord saved = pointRecordRepository.save(pointRecord);
       return PointRecordMapper.toDTO(saved);

    }

    public List<PointRecordDTO> getPointRecord() {
        return pointRecordRepository.findAll()
                .stream()
                .map(PointRecordMapper :: toDTO)
                .toList();
    }

    @Transactional
    public PointRecord updatePointRecord(Long id, PointRecordDTO data) {
        PointRecord pointRecord = pointRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PointRecord not found"));

        if (data.startTime() != null) {
            pointRecord.setStartTime(data.startTime());
        }
        if (data.endTime() != null) {
            pointRecord.setEndTime(data.endTime());
        }
        if (data.date() != null) {
            pointRecord.setDate(data.date());
        }
        return pointRecordRepository.save(pointRecord);
    }

    @Transactional
    public void deletePointRecord(Long id) {
        var pointRecord = pointRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PointRecord not found"));

        pointRecordRepository.deleteById(pointRecord.getId());
    }

}
