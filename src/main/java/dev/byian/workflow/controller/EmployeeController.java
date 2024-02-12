package dev.byian.workflow.controller;

import dev.byian.workflow.domain.Employee;
import dev.byian.workflow.domain.Employee;
import dev.byian.workflow.service.EmployeeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAllEmployees(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return employeeService.getAllEmployees(pageRequest);
    }

    @GetMapping(value = "/find", params = "id")
    public ResponseEntity<Map<String,Object>> getEmployeeById(@RequestParam("id") UUID id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(value = "/find",params = "email")
    public ResponseEntity<Map<String, Object>> getEmployeeByEmail(@RequestParam("email") String email) {
        return employeeService.getEmployeeByEmail(email);
    }

    @DeleteMapping(value = "/delete", params = "id")
    public ResponseEntity<Map<String,String>> deleteEmployee(@RequestParam("id") UUID id) {
        return employeeService.deleteEmployee(id);
    }

    @PostMapping(value = "/add", params = "departmentID")
    public ResponseEntity<Map<String,Object>> addEmployee(@RequestBody Employee employee, @RequestParam("departmentID") UUID departmentID){
        return employeeService.addEmployee(employee,departmentID);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateEmployee(@RequestBody Employee Employee) {
        return employeeService.updateEmployee(Employee);
    }

    @GetMapping(value = "/generate")
    public ResponseEntity <Map<String, Object>> generateEmployees(@RequestParam("count") int count, @RequestParam("departmentID") UUID departmentID) {
        return employeeService.generateFakeEmployee(count, departmentID);
    }

}
