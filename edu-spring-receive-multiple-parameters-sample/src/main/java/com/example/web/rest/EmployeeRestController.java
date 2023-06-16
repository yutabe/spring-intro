package com.example.web.rest;

import com.example.persistence.entity.Employee;
import com.example.service.EmployeeService;
import com.example.web.request.EmployeeRequest;
import com.example.web.request.EmployeeSearchParams;
import com.example.web.response.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> findByNameContainingAndEmailContaining(@Validated EmployeeSearchParams params) {
        List<Employee> employeeList = employeeService.findByNameContainingAndEmailContaining(params.getName(), params.getEmail());
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeResponse employeeResponse = new EmployeeResponse(employee.getId(),
                    employee.getName(),
                    employee.getJoinedDate(),
                    employee.getDepartmentName(),
                    employee.getEmail(),
                    employee.getBirthDay());
            employeeResponseList.add(employeeResponse);
        }
        return employeeResponseList;
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Integer id) {
        Employee employee = employeeService.findById(id);
        EmployeeResponse employeeResponse = new EmployeeResponse(employee.getId(),
                employee.getName(),
                employee.getJoinedDate(),
                employee.getDepartmentName(),
                employee.getEmail(),
                employee.getBirthDay());
        return employeeResponse;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Validated EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(request.getName());
        employee.setJoinedDate(request.getJoinedDate());
        employee.setDepartmentName(request.getDepartmentName());
        employee.setEmail(request.getEmail());
        employee.setBirthDay(request.getBirthDay());
        employeeService.update(employee);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Validated EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setJoinedDate(request.getJoinedDate());
        employee.setDepartmentName(request.getDepartmentName());
        employee.setEmail(request.getEmail());
        employee.setBirthDay(request.getBirthDay());
        employeeService.insert(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment(employee.getId().toString())
                .build().encode().toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        employeeService.delete(id);
    }

}
