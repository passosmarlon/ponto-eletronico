package com.ponto.eletronico.controller;

import com.ponto.eletronico.dto.EmployeeDTO;
import com.ponto.eletronico.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO data) {
            employeeService.saveEmployee(data);
            return ResponseEntity.ok(data);
        }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        var get = employeeService.getEmployee();
        return ResponseEntity.ok(get);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable Long id ,
                                              @RequestBody EmployeeDTO data) {
        employeeService.updateEmployee(id, data);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    }


