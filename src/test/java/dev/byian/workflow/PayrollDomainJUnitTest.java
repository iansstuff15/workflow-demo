package dev.byian.workflow;
import dev.byian.workflow.domain.Employee;
import dev.byian.workflow.domain.Payroll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PayrollDomainJUnitTest {
   @Test
    public void payrollShouldHaveGrossPay() {
       Payroll payroll = new Payroll();
       payroll.setGrossPay(1000);
       Assertions.assertEquals(1000, payroll.getGrossPay());
    }
    @Test
    public void payrollShouldHaveNetPay() {
        Payroll payroll = new Payroll();
        payroll.setNetPay(1000);
        Assertions.assertEquals(1000, payroll.getNetPay());
    }
    @Test
    public void payrollShouldHaveDeductions() {
        Payroll payroll = new Payroll();
        payroll.setDeductions(1000);
        Assertions.assertEquals(1000, payroll.getDeductions());
    }
    @Test
    public void payrollShouldHaveEmployee() {
        Payroll payroll = new Payroll();
        payroll.setEmployee(new Employee());
        Assertions.assertNotNull(payroll.getEmployee());
    }
    @Test
    public void payrollCanHaveAssignedID() {
        Payroll payroll = new Payroll();
        payroll.setId(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"));
        Assertions.assertEquals("f47ac10b-58cc-4372-a567-0e02b2c3d479",payroll.getId().toString());
    }
    @Test
    public void payrollCanBeUpdatedFromAnotherPayroll() {
        Payroll payroll = new Payroll();
        payroll.setGrossPay(1000);
        payroll.setDeductions(100);
        Payroll newPayroll = new Payroll();
        newPayroll.setGrossPay(2000);
        newPayroll.setDeductions(200);
        payroll.update(newPayroll);
        Assertions.assertEquals(2000, payroll.getGrossPay());
        Assertions.assertEquals(1800, payroll.getNetPay());
        Assertions.assertEquals(200, payroll.getDeductions());
    }
    @Test
    public void payrollDeductionCanBeUpdatedFromAnotherPayroll(){
        Payroll payroll = new Payroll();
        payroll.setGrossPay(1000);
        payroll.setDeductions(100);
        Payroll newPayroll = new Payroll();
        newPayroll.setDeductions(200);
        payroll.update(newPayroll);
        Assertions.assertEquals(1000, payroll.getGrossPay());
        Assertions.assertEquals(800, payroll.getNetPay());
        Assertions.assertEquals(200, payroll.getDeductions());
    }
    @Test
    public  void  payrollGrossPayCanBeUpdatedFromAnotherPayroll(){
        Payroll payroll = new Payroll();
        payroll.setGrossPay(1000);
        payroll.setDeductions(100);
        Payroll newPayroll = new Payroll();
        newPayroll.setGrossPay(2000);
        payroll.update(newPayroll);
        Assertions.assertEquals(2000, payroll.getGrossPay());
        Assertions.assertEquals(1900, payroll.getNetPay());
        Assertions.assertEquals(100, payroll.getDeductions());
    }
    @Test
    public  void payrollGrossPayCanBeUpdatedFromPayroll1ToPayroll2GrossPay(){
        Payroll payroll = new Payroll();
        payroll.setGrossPay(1000);
        payroll.update(Payroll.builder().grossPay(2000).build());
        Assertions.assertEquals(2000, payroll.getGrossPay());
    }
    @Test
    public  void payrollDeductionsCanBeUpdatedFromPayroll1ToPayroll2Deductions(){
        Payroll payroll = new Payroll();
        payroll.setDeductions(100);
        payroll.update(Payroll.builder().deductions(200).build());
        Assertions.assertEquals(200, payroll.getDeductions());
    }
}
