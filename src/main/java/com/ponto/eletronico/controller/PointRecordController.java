package com.ponto.eletronico.controller;

import com.ponto.eletronico.dto.PointRecordDTO;
import com.ponto.eletronico.service.PointRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("point")
public class PointRecordController {

    private final PointRecordService pointRecordService;

    public PointRecordController(PointRecordService pointRecordService) {
        this.pointRecordService = pointRecordService;
    }

    @PreAuthorize("hasHole('EMPLOYEE')")
    @PostMapping
    public ResponseEntity<PointRecordDTO> save(@RequestBody PointRecordDTO data) {
        var saved = pointRecordService.savePointRecord(data);
        return ResponseEntity.ok(saved);
    }

    @PreAuthorize("hasAnyHole('ADMIN', 'HR')")
    @GetMapping
    public ResponseEntity<List<PointRecordDTO>> getAll() {
        var get = pointRecordService.getPointRecord();
        return ResponseEntity.ok(get);
    }

    @PreAuthorize("hasHole('EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<PointRecordDTO> getPointRecorId(@PathVariable Long id) {
        var get = pointRecordService.getPointRecorId(id);
        return ResponseEntity.ok(get);
    }

    @PreAuthorize("hasAnyHole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<PointRecordDTO> update(@PathVariable Long id,
                                                 @RequestBody PointRecordDTO data) {
        var up = pointRecordService.updatePointRecord(id, data);
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasHole('EMPLOYEE')")
    @PutMapping
    public ResponseEntity<PointRecordDTO> endTime(@RequestBody PointRecordDTO data) {
        var saved = pointRecordService.saveEndPointRecord(data, data.employeeId());
        return ResponseEntity.ok(saved);
    }

    @PreAuthorize("hasAnyHole('ADMIN', 'HR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pointRecordService.deletePointRecord(id);

        return ResponseEntity.notFound().build();
    }

}
