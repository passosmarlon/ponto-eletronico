package com.ponto.eletronico.controller;

import com.ponto.eletronico.dto.PointRecordDTO;
import com.ponto.eletronico.service.PointRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("point")
public class PointRecordController {

    private final PointRecordService pointRecordService;

    public PointRecordController(PointRecordService pointRecordService) {
        this.pointRecordService = pointRecordService;
    }

    @PostMapping
    public ResponseEntity<PointRecordDTO> save(@RequestBody PointRecordDTO data) {
        var saved = pointRecordService.savePointRecord(data);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<PointRecordDTO>> getAll() {
        var get = pointRecordService.getPointRecord();
        return ResponseEntity.ok(get);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PointRecordDTO> update(@PathVariable Long id,
                                                 @RequestBody PointRecordDTO data) {
        var up = pointRecordService.updatePointRecord(id, data);
        return ResponseEntity.ok(data);
    }

    @PutMapping
    public ResponseEntity<PointRecordDTO> endTime(@RequestBody PointRecordDTO data) {
        var saved = pointRecordService.saveEndPointRecord(data, data.employeeId());
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pointRecordService.deletePointRecord(id);

        return ResponseEntity.notFound().build();
    }

}
