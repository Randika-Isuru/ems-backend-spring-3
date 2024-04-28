package com.ayubowanrandy.ems.service.impl;

import com.ayubowanrandy.ems.dto.EmployeeDto;
import com.ayubowanrandy.ems.entity.Employee;
import com.ayubowanrandy.ems.exception.ResourceNotFoundException;
import com.ayubowanrandy.ems.mapper.EmployeeMapper;
import com.ayubowanrandy.ems.repositoty.EmployeeRepository;
import com.ayubowanrandy.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapTOEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id : " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDto) {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id : " + employeeId));
        employee.setFirstName(updatedEmployeeDto.getFirstName());
        employee.setLastName(updatedEmployeeDto.getLastName());
        employee.setEmail(updatedEmployeeDto.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id : " + employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
