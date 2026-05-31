package com.ponto.eletronico.controller;

import com.ponto.eletronico.dto.EmployeeDTO;
import com.ponto.eletronico.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO data) {
            employeeService.saveEmployee(data);
            return ResponseEntity.ok(data);
        }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        var get = employeeService.getEmployee();
        return ResponseEntity.ok(get);
    }

    @PreAuthorize("hasHole('EMPLOYEE')")
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


