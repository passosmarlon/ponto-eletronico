package com.ponto.eletronico.service;

import com.ponto.eletronico.dto.EmployeeDTO;
import com.ponto.eletronico.entity.Employee;
import com.ponto.eletronico.mapper.EmployeeMapper;
import com.ponto.eletronico.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Transactional
    public Employee saveEmployee(EmployeeDTO data) {
        Employee employee = EmployeeMapper.toEntity(data);
        //Employee employee = new Employee(data);
        return employeeRepository.save(employee);

    }

    public EmployeeDTO getEmployeeId(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return EmployeeMapper.toDTO(employee);
    }

    public List<EmployeeDTO> getEmployee() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    @Transactional
        public Employee updateEmployee(Long id, EmployeeDTO data) {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

                if (data.name() != null) {
                    employee.setName(data.name());
                }
                if (data.email() != null) {
                    employee.setEmail(data.email());
                }
                if (data.salary() != null) {
                    employee.setSalary(data.salary());
                }
                if (data.workedHours() != null) {
                    employee.setHoursWorked(data.workedHours());
                }

            return employeeRepository.save(employee);
        }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));

            employeeRepository.deleteById(employee.getId());

        }
    }






