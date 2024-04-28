package com.ayubowanrandy.ems.mapper;

import com.ayubowanrandy.ems.dto.EmployeeDto;
import com.ayubowanrandy.ems.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
    }

    public static Employee mapTOEmployee(EmployeeDto employeeDto){
        return new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail());
    }
}
