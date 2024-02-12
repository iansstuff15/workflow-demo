package dev.byian.workflow.service;

import com.github.javafaker.Faker;
import dev.byian.workflow.dao.DepartmentDao;
import dev.byian.workflow.dao.EmployeeDao;
import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.Employee;
import dev.byian.workflow.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    EmployeeDao employeeDao;
    DepartmentDao departmentDao;
    EmployeeService(EmployeeDao employeeDao, DepartmentDao departmentDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
    }
    public ResponseEntity<Map<String, Object>> getAllEmployees(PageRequest pageRequest) {
        Map<String, Object> responseMap = new HashMap<>();
       try {
           Page<Employee> employees = employeeDao.findAll(pageRequest);
           if (employees.isEmpty()) {
               responseMap.put("message", "No employee found");
               responseMap.put("size", 0);
               return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
           }
           responseMap.put("size", employees.get().count());
           responseMap.put("data", employees);
           return new ResponseEntity<>(responseMap, HttpStatus.OK);
       } catch (Exception e) {
           responseMap.put("message", e.getMessage());
           responseMap.put("status", HttpStatus.BAD_REQUEST.toString());
           return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<Map<String,Object>> addEmployee(Employee employee, UUID departmentID) {
        Map<String, Object> responseMap = new HashMap<>();
       try {
           Optional<Department> departmentResponse = departmentDao.findById(departmentID);
           if(departmentResponse.isEmpty()){
               responseMap.put("message", "No department found with ID " + departmentID);
               return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
           }
           employee.setDepartment(departmentResponse.get());
           employeeDao.save(employee);
           responseMap.put("message", "Employee " + employee.getId() + " added successfully");
              responseMap.put("data", employee);
           return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
       } catch (Exception e) {
           responseMap.put("message", e.getMessage());
           return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<Map<String,String>> deleteEmployee(UUID id) {
        Map<String, String> responseMap = new HashMap<>();
        try {
            employeeDao.deleteById(id);
            responseMap.put("message", "Employee deleted successfully with ID of " +id);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> updateEmployee(Employee employee) {
        Map<String,Object> responseMap = new HashMap<>();

        try {
            Optional<Employee> employeeResponse =   employeeDao.findById(employee.getId());
            if(employeeResponse.isEmpty()){
                responseMap.put("message", "No supplier found with ID " + employee.getId());
                return new ResponseEntity<>(responseMap, HttpStatus.NO_CONTENT);
            }
            employeeResponse.get().update(employee);
            employeeDao.save(employeeResponse.get());
            responseMap.put("message", "Employee updated successfully with ID of " + employee.getId());
            responseMap.put("data", employeeResponse.get());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> getEmployeeById(UUID id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Optional<Employee> employee = employeeDao.findById(id);
            if (employee.isEmpty()) {
                responseMap.put("message", "No employee found with ID " + id);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", 1);
            responseMap.put("data", employee.get());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity <Map<String, Object>> generateFakeEmployee(int count, UUID departmentID){
        Map<String, Object> responseMap = new HashMap<>();
        List<Employee> employeeList = new ArrayList<>();
        try {
            int savedCounter = 0;
            int duplicateCounter = 0;
            Optional<Department> departmentResponse = departmentDao.findById(departmentID);
            if(departmentResponse.isEmpty()){
                responseMap.put("message", "No department found with ID " + departmentID);
                return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
            }

            for(int i = 0; i < count; i++){
                Employee employee = new Employee();
                Faker faker = new Faker();
                employee.setFirstName(faker.name().firstName());
                employee.setLastName(faker.name().lastName());
                employee.setEmail(employee.getFirstName()+employee.getLastName()+"@"+ faker.internet().domainName());
                employee.setDepartment(departmentResponse.get());
                employee.setPhone(faker.phoneNumber().cellPhone());
                employee.setPosition(faker.job().title());
                employee.setDailyRate(faker.number().randomDouble(2, 100, 1000));
                employee.setEmergencyLeaveCredits(faker.number().randomDigit());
                employee.setMaternityLeaveCredits(faker.number().randomDigit());
                employee.setPaternityLeaveCredits(faker.number().randomDigit());
                employee.setSickLeaveCredits(faker.number().randomDigit());
                employee.setVacationLeaveCredits(faker.number().randomDigit());
                List<String> paySchedules = Arrays.asList("Weekly", "Bi-Weekly", "Monthly");
                int randomIndex = new Random().nextInt(paySchedules.size());
                employee.setPaySchedule(paySchedules.get(randomIndex));
                Optional<Employee> employeeResponse = employeeDao.findByEmail(employee.getEmail());
                if(employeeResponse.isEmpty()){
                    employeeDao.save(employee);
                    savedCounter++;
                }
                else{
                    duplicateCounter++;
                }
                employeeList.add(employee);
            }
            responseMap.put("data", employeeList);
            responseMap.put("message", savedCounter + " fake employee added successfully with " + duplicateCounter + " duplicates");
            return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String, Object>> getEmployeeByEmail(String email) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Optional<Employee> employee = employeeDao.findByEmail(email);
            if (employee.isEmpty()) {
                responseMap.put("message", "No employee found with email " + email);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", 1);
            responseMap.put("data", employee.get());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }
}
