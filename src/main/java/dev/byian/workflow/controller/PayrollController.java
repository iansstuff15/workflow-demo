package dev.byian.workflow.controller;

import dev.byian.workflow.domain.Payroll;
import dev.byian.workflow.service.PayrollService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {
    PayrollService payrollService;
    PayrollController(PayrollService payrollService){
        this.payrollService = payrollService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAllPayrolls(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return payrollService.getAllPayroll(pageRequest);
    }

    @GetMapping(value = "/find", params = "id")
    public ResponseEntity<Map<String,Object>> getPayrollById(@RequestParam("id") UUID id) {
        return payrollService.getPayrollById(id);
    }

    @DeleteMapping(value = "/delete", params = "id")
    public ResponseEntity<Map<String,String>> deletePayroll(@RequestParam("id") UUID id) {
        return payrollService.deletePayroll(id);
    }

    @PostMapping(value = "/add", params = "employeeID")
    public ResponseEntity<Map<String,Object>> addPayroll(@RequestBody Payroll Payroll, @RequestParam("employeeID") UUID employeeID ){
        return payrollService.addPayroll(Payroll, employeeID);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePayroll(@RequestBody Payroll Payroll) {
        return payrollService.updatePayroll(Payroll);
    }

    @GetMapping(value = "/generate", params = {"count", "employeeID"})
    public ResponseEntity <Map<String, Object>> generatePayrolls(@RequestParam("count") int count, @RequestParam("employeeID") UUID employeeID) {
        return payrollService.generateFakePayroll(count,employeeID);
    }

}
