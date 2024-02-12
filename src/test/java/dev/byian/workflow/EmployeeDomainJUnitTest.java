package dev.byian.workflow;

import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EmployeeDomainJUnitTest {
    @Test
    void employeeCanBeAssignedAtITDepartment() {
        Department department = new Department();
        department.setName("IT");
        Employee employee = new Employee();
        employee.setDepartment(department);
        Assertions.assertEquals("IT", employee.getDepartment().getName());
    }
    @Test
    void employeeCanHaveFirstName() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        Assertions.assertEquals("John", employee.getFirstName());
    }
    @Test
    void employeeCanHaveLastName() {
        Employee employee = new Employee();
        employee.setLastName("Doe");
        Assertions.assertEquals("Doe", employee.getLastName());
    }
    @Test
    void employeeCanHavePhone() {
        Employee employee = new Employee();
        employee.setPhone("1234567890");
        Assertions.assertEquals("1234567890", employee.getPhone());
    }
    @Test
    void employeeCanHavePosition() {
        Employee employee = new Employee();
        employee.setPosition("Software Engineer");
        Assertions.assertEquals("Software Engineer", employee.getPosition());
    }
    @Test
    void employeeCanHaveDailyRate() {
        Employee employee = new Employee();
        employee.setDailyRate(1000.0);
        Assertions.assertEquals(1000.0, employee.getDailyRate());
    }
    @Test
    void employeeCanHavePaySchedule() {
        Employee employee = new Employee();
        employee.setPaySchedule("monthly");
        Assertions.assertEquals("monthly", employee.getPaySchedule());
    }
    @Test
    void employeeCanHaveAssignedLeaveCredits() {
        Employee employee = new Employee();
        employee.setSickLeaveCredits(10);
        employee.setVacationLeaveCredits(10);
        employee.setEmergencyLeaveCredits(10);
        employee.setPaternityLeaveCredits(10);
        employee.setMaternityLeaveCredits(10);
        Assertions.assertEquals(10, employee.getSickLeaveCredits());
        Assertions.assertEquals(10, employee.getVacationLeaveCredits());
        Assertions.assertEquals(10, employee.getEmergencyLeaveCredits());
        Assertions.assertEquals(10, employee.getPaternityLeaveCredits());
        Assertions.assertEquals(10, employee.getMaternityLeaveCredits());
    }
    @Test
    void employeeHaveDefaultLeaveCredits() {
        Employee employee = new Employee();
        Assertions.assertEquals(15, employee.getSickLeaveCredits());
        Assertions.assertEquals(15, employee.getVacationLeaveCredits());
        Assertions.assertEquals(15, employee.getEmergencyLeaveCredits());
        Assertions.assertEquals(0, employee.getPaternityLeaveCredits());
        Assertions.assertEquals(0, employee.getMaternityLeaveCredits());
    }

    @Test
    void employeeCanHaveEmail() {
        Employee employee = new Employee();
        employee.setEmail("test@email.com");
        Assertions.assertEquals("test@email.com", employee.getEmail());
    }
    @Test
    void employeeCanHaveAssigneedId() {
        Employee employee = new Employee();
        employee.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        Assertions.assertEquals("00000000-0000-0000-0000-000000000001", employee.getId().toString());
    }
    @Test
    void employeeFirstNameCanBeUpdatedFromEmployeeFirstNameToEmployee2FirstName(){
        Employee employee = new Employee();
        employee.setFirstName("Employee 1");
        employee.update(Employee.builder().firstName("Employee 2").build());
        String firstName = employee.getFirstName();
        Assertions.assertEquals("Employee 2", firstName);
    }
    @Test
    void employeeLastNameCanBeUpdatedFromEmployee1LastNameToEmployee2LastName(){
        Employee employee = new Employee();
        employee.setLastName("Employee 1");
        employee.update(Employee.builder().lastName("Employee 2").build());
        String lastName = employee.getLastName();
        Assertions.assertEquals("Employee 2", lastName);
    }
    @Test
    void employeePhoneCanBeUpdatedFromEmployee1PhoneToEmployee2Phone(){
        Employee employee = new Employee();
        employee.setPhone("1234567890");
        employee.update(Employee.builder().phone("0987654321").build());
        String phone = employee.getPhone();
        Assertions.assertEquals("0987654321", phone);
    }
    @Test
    void employeePositionCanBeUpdatedFromEmployee1PositionToEmployee2Position(){
        Employee employee = new Employee();
        employee.setPosition("Employee 1");
        employee.update(Employee.builder().position("Employee 2").build());
        String position = employee.getPosition();
        Assertions.assertEquals("Employee 2", position);
    }
    @Test
    void employeeEmailCanBeUpdatedFromEmployee1EmailToEmployee2Email() {
        Employee employee = new Employee();
        employee.setEmail("test@email.com");
        employee.update(Employee.builder().email("test2@email.com").build());
        String email = employee.getEmail();
        Assertions.assertEquals("test2@email.com", email);
    }
    @Test
    void employeeDailyRateCanBeUpdatedFromEmployee1DailyRateToEmployee2DailyRate() {
        Employee employee = new Employee();
        employee.setDailyRate(500.0);
        employee.update(Employee.builder().dailyRate(1000.0).build());
        double dailyRate = employee.getDailyRate();
        Assertions.assertEquals(1000.0, dailyRate);
    }
    @Test
    void employeeCanBeUpdatedWithNewEmployee() {
        Employee employee = new Employee();
        Employee newEmployee = new Employee();
        employee.setFirstName("Jane");
        employee.setLastName("Doe");
        employee.setPhone("0987654321");
        employee.setPosition("Software Developer");
        employee.setEmail("test@email.com");
        employee.setDailyRate(500.0);
        employee.setPaySchedule("bi-monthly");
        employee.setSickLeaveCredits(5);
        employee.setVacationLeaveCredits(5);
        employee.setEmergencyLeaveCredits(5);
        employee.setPaternityLeaveCredits(5);
        employee.setMaternityLeaveCredits(5);

        newEmployee.setFirstName("John");
        newEmployee.setLastName("Doe");
        newEmployee.setPhone("1234567890");
        newEmployee.setPosition("Software Engineer");
        newEmployee.setEmail("test2@email.com");
        newEmployee.setDailyRate(1000.0);
        newEmployee.setPaySchedule("monthly");
        newEmployee.setSickLeaveCredits(10);
        newEmployee.setVacationLeaveCredits(10);
        newEmployee.setEmergencyLeaveCredits(10);
        newEmployee.setPaternityLeaveCredits(10);
        newEmployee.setMaternityLeaveCredits(10);
        newEmployee.setDepartment(new Department());
        employee.update(newEmployee);

        Assertions.assertEquals("John", employee.getFirstName());
        Assertions.assertEquals("Doe", employee.getLastName());
        Assertions.assertEquals("1234567890", employee.getPhone());
        Assertions.assertEquals("Software Engineer", employee.getPosition());
        Assertions.assertEquals(1000.0, employee.getDailyRate());
        Assertions.assertEquals("monthly", employee.getPaySchedule());
        Assertions.assertEquals(10, employee.getSickLeaveCredits());
        Assertions.assertEquals(10, employee.getVacationLeaveCredits());
        Assertions.assertEquals(10, employee.getEmergencyLeaveCredits());
        Assertions.assertEquals(10, employee.getPaternityLeaveCredits());
        Assertions.assertEquals(10, employee.getMaternityLeaveCredits());

    }
}
