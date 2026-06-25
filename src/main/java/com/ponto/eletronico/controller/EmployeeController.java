package com.ponto.eletronico.controller;

import com.ponto.eletronico.dto.EmployeeDTO;
import com.ponto.eletronico.entity.Employee;
import com.ponto.eletronico.mapper.EmployeeMapper;
import com.ponto.eletronico.service.EmployeeService;
import com.ponto.eletronico.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO data) {
        Employee saved = employeeService.saveEmployee(data);
        return ResponseEntity.ok(EmployeeMapper.toDTO(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR', ''EMPLOYEE)")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        var get = employeeService.getEmployee();
        return ResponseEntity.ok(get);
    }

    // EmployeeController
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/me")
    public ResponseEntity<EmployeeDTO> getMe(@AuthenticationPrincipal Users user) {
        if (user.getEmployeeId() == null) {
            return ResponseEntity.notFound().build();
        }
        EmployeeDTO dto = employeeService.getEmployeeId(user.getEmployeeId());
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeId(@PathVariable Long id) {
        var get = employeeService.getEmployeeId(id);
        return ResponseEntity.ok(get);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable Long id ,
                                              @RequestBody EmployeeDTO data) {
        employeeService.updateEmployee(id, data);
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    }


