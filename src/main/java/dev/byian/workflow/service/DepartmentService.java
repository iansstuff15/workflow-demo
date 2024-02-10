package dev.byian.workflow.service;

import com.github.javafaker.Faker;
import dev.byian.workflow.dao.DepartmentDao;
import dev.byian.workflow.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {

    DepartmentDao departmentDao;
    DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
    public ResponseEntity<Map<String, Object>> getAllDepartments(PageRequest pageRequest) {
        Map<String, Object> responseMap = new HashMap<>();
       try {
           Page<Department> departments = departmentDao.findAll(pageRequest);
           if (departments.isEmpty()) {
               responseMap.put("message", "No department found");
               responseMap.put("size", 0);
               return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
           }
           responseMap.put("size", departments.get().count());
           responseMap.put("data", departments);
           return new ResponseEntity<>(responseMap, HttpStatus.OK);
       } catch (Exception e) {
           responseMap.put("message", e.getMessage());
           responseMap.put("status", HttpStatus.BAD_REQUEST.toString());
           return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<Map<String,String>> addDepartment(Department department) {
        Map<String, String> responseMap = new HashMap<>();
       try {
           departmentDao.save(department);
           responseMap.put("message", "Department " + department.getName() + " added successfully");
           return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
       } catch (Exception e) {
           responseMap.put("message", e.getMessage());
           return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<Map<String,String>> deleteDepartment(UUID id) {
        Map<String, String> responseMap = new HashMap<>();
        try {
            departmentDao.deleteById(id);
            responseMap.put("message", "Department deleted successfully with ID of " +id);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,String>> updateDepartment(Department department) {
        Map<String,String> responseMap = new HashMap<>();
        try {
            departmentDao.save(department);
            responseMap.put("message", "Department updated successfully with ID of " + department.getId() + " and name of " + department.getName());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String, Object>> getDepartmentByName(String name) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            List<Department> departments = departmentDao.findByName(name);
            if (departments.isEmpty()) {
                responseMap.put("message", "No department found with name " + name);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", departments.size());
            responseMap.put("data", departments);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> getDepartmentById(UUID id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Optional<Department> department = departmentDao.findById(id);
            if (department.isEmpty()) {
                responseMap.put("message", "No department found with ID " + id);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", 1);
            responseMap.put("data", department.get());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity <Map<String, String>> generateFakeDepartment(int count){
        Map<String, String> responseMap = new HashMap<>();
        try {
            int savedCounter = 0;
            int duplicateCounter = 0;
            for(int i = 0; i < count; i++){
                Department department = new Department();
                Faker faker = new Faker();
                String departmentName = faker.commerce().department();
                List<Department> departmentResponse = departmentDao.findByName(departmentName);
                if(departmentResponse.isEmpty()){
                    department.setName(departmentName);
                    departmentDao.save(department);
                    savedCounter++;
                }
                else{
                    duplicateCounter++;
                }

            }
            responseMap.put("message", savedCounter + " fake department added successfully with " + duplicateCounter + " duplicates");
            return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }


}
