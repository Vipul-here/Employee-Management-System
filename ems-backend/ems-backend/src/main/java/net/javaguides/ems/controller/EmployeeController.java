package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.javaguides.ems.entity.Employee;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    // Build Add employee Rest API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
      //  Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
       EmployeeDto savedEmployee =  employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build get employee rest api
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long employeeId){
       EmployeeDto employeeDto= employeeService.getEmployeebyId(employeeId);
       return ResponseEntity.ok(employeeDto);
    }

    //build get all employees rest api
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
      List<EmployeeDto> employees=  employeeService.getAllEmployees();
      return ResponseEntity.ok(employees);
    }

    //build update employee rest api
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long employeeId, @RequestBody EmployeeDto updatedEmployee){
      EmployeeDto employeeDto=  employeeService.updateEmployee(employeeId, updatedEmployee);
      return ResponseEntity.ok(employeeDto);
    }

    //build delete employee rest api
    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted Successfully!");
    }
}
