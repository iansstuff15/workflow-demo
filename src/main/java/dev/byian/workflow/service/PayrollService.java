package dev.byian.workflow.service;

import com.github.javafaker.Faker;
import dev.byian.workflow.dao.EmployeeDao;
import dev.byian.workflow.dao.PayrollDao;
import dev.byian.workflow.domain.Employee;
import dev.byian.workflow.domain.Payroll;
import dev.byian.workflow.domain.Payroll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PayrollService {

    PayrollDao payrollDao;
    EmployeeDao employeeDao;
    PayrollService(PayrollDao payrollDao, EmployeeDao employeeDao){
        this.payrollDao = payrollDao;
        this.employeeDao = employeeDao;
    }
    public ResponseEntity<Map<String, Object>> getAllPayroll(PageRequest pageRequest) {
        Map<String, Object> responseMap = new HashMap<>();
       try {
           Page<Payroll> payrolls = payrollDao.findAll(pageRequest);
           if (payrolls.isEmpty()) {
               responseMap.put("message", "No payrolls found");
               responseMap.put("size", 0);
               return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
           }
           responseMap.put("size", payrolls.get().count());
           responseMap.put("data", payrolls);
           return new ResponseEntity<>(responseMap, HttpStatus.OK);
       } catch (Exception e) {
           responseMap.put("message", e.getMessage());
           responseMap.put("status", HttpStatus.BAD_REQUEST.toString());
           return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<Map<String,Object>> addPayroll(Payroll payroll, UUID employeeID) {
        Map<String, Object> responseMap = new HashMap<>();

       try {
           Optional<Employee> employeeResponse = employeeDao.findById(employeeID);
           if(employeeResponse.isEmpty()){
               responseMap.put("message", "No employee found with ID " + employeeID);
               return new ResponseEntity<>(responseMap, HttpStatus.NO_CONTENT);
           }
           payroll.setNetPay(payroll.getGrossPay() - payroll.getDeductions());
           payroll.setEmployee(employeeResponse.get());
           payrollDao.save(payroll);
           responseMap.put("message", "Payroll " + payroll.getId() + " added successfully");
           responseMap.put("data", payroll);
           return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
       } catch (Exception e) {
           responseMap.put("message", e.getMessage());
           return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<Map<String,String>> deletePayroll(UUID id) {
        Map<String, String> responseMap = new HashMap<>();
        try {
            payrollDao.deleteById(id);
            responseMap.put("message", "Payroll deleted successfully with ID of " +id);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> updatePayroll(Payroll payroll) {
        Map<String,Object> responseMap = new HashMap<>();

        try {
            Optional<Payroll> payrollResponse =   payrollDao.findById(payroll.getId());
            if(payrollResponse.isEmpty()){
                responseMap.put("message", "No payroll found with ID " + payroll.getId());
                return new ResponseEntity<>(responseMap, HttpStatus.NO_CONTENT);
            }
            payrollResponse.get().update(payroll);
            payrollDao.save(payrollResponse.get());
            responseMap.put("message", "Payroll updated successfully with ID of " + payroll.getId());
            responseMap.put("data", payrollResponse.get());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);

        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> getPayrollById(UUID id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Optional<Payroll> payroll = payrollDao.findById(id);
            if (payroll.isEmpty()) {
                responseMap.put("message", "No Payroll found with ID " + id);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", 1);
            responseMap.put("data", payroll.get());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity <Map<String, Object>> generateFakePayroll(int count, UUID employeeId) {
        Map<String, Object> responseMap = new HashMap<>();
        List<Payroll> payrollList = new ArrayList<>();
        try {
            int savedCounter = 0;

            Optional<Employee> employeeResponse = employeeDao.findById(employeeId);
            if(employeeResponse.isEmpty()){
                responseMap.put("message", "No employee found with ID " + employeeId);
                return new ResponseEntity<>(responseMap, HttpStatus.NO_CONTENT);
            }
            for(int i = 0; i < count; i++){
                Payroll payroll = new Payroll();
                Faker faker = new Faker();
                payroll.setGrossPay((float) faker.number().randomDouble(2, 1000, 10000));
                payroll.setDeductions((float) faker.number().randomDouble(2, 100, 1000));
                payroll.setNetPay(payroll.getGrossPay() - payroll.getDeductions());
                payroll.setEmployee(employeeResponse.get());
                payrollDao.save(payroll);
                savedCounter++;
                payrollList.add(payroll);
            }
            responseMap.put("message", savedCounter + " fake Payroll added successfully ");
            responseMap.put("data", payrollList);
            return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

}
