package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;
import net.javaguides.ems.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceimpl implements EmployeeService {

    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeebyId(long employeeId) {
       Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with given id : " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employees= employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(long employeeId, EmployeeDto updateEmployee) {
       Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee does not exost with given id : " + employeeId)
        );

       employee.setFirstName(updateEmployee.getFirstname());
       employee.setLastName(updateEmployee.getLastname());
       employee.setEmail(updateEmployee.getEmail());
      Employee updatedEmployeeObj= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee does not exost with given id : " + employeeId)
        );
       employeeRepository.deleteById(employeeId);
    }
}
