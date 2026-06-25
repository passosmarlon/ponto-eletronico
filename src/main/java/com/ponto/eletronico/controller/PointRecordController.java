package com.ponto.eletronico.controller;

import com.ponto.eletronico.dto.PointRecordDTO;
import com.ponto.eletronico.service.PointRecordService;
import com.ponto.eletronico.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("point")
@RequiredArgsConstructor
public class PointRecordController {

    private final PointRecordService pointRecordService;

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping
    public ResponseEntity<PointRecordDTO> save(@RequestBody PointRecordDTO data) {
        var saved = pointRecordService.savePointRecord(data);
        return ResponseEntity.ok(saved);
    }

    // PointRecordController
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/me")
    public ResponseEntity<List<PointRecordDTO>> getMyRecords(@AuthenticationPrincipal Users user) {
        List<PointRecordDTO> records = pointRecordService.getPointRecordsByEmployee(user.getEmployeeId());
        return ResponseEntity.ok(records);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @GetMapping
    public ResponseEntity<List<PointRecordDTO>> getAll() {
        var get = pointRecordService.getPointRecord();
        return ResponseEntity.ok(get);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<PointRecordDTO> getPointRecorId(@PathVariable Long id) {
        var get = pointRecordService.getPointRecorId(id);
        return ResponseEntity.ok(get);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<PointRecordDTO> update(@PathVariable Long id,
                                                 @RequestBody PointRecordDTO data) {
        var up = pointRecordService.updatePointRecord(id, data);
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PutMapping
    public ResponseEntity<PointRecordDTO> endTime(@RequestBody PointRecordDTO data) {
        var saved = pointRecordService.saveEndPointRecord(data, data.employeeId());
        return ResponseEntity.ok(saved);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pointRecordService.deletePointRecord(id);

        return ResponseEntity.notFound().build();
    }

}
