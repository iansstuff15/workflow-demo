package dev.byian.workflow.controller;

import dev.byian.workflow.domain.Department;
import dev.byian.workflow.service.DepartmentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    DepartmentService departmentService;
    DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAllDepartments(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return departmentService.getAllDepartments(pageRequest);
    }

    @GetMapping(value = "/find", params = "id")
    public ResponseEntity<Map<String,Object>> getDepartmentById(@RequestParam("id") UUID id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping(value = "/find",params = "name")
    public ResponseEntity<Map<String, Object>> getDepartmentByName(@RequestParam("name") String name) {
        return departmentService.getDepartmentByName(name);
    }

    @DeleteMapping(value = "/delete", params = "id")
    public ResponseEntity<Map<String,String>> deleteDepartment(@RequestParam("id") UUID id) {
        return departmentService.deleteDepartment(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateDepartment(@RequestBody Department department) {
        return departmentService.updateDepartment(department);
    }

    @GetMapping(value = "/generate", params = "count")
    public ResponseEntity <Map<String, Object>> generateDepartments(@RequestParam("count") int count) {
        return departmentService.generateFakeDepartment(count);
    }

}
