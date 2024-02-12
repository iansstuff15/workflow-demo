package dev.byian.workflow;

import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeDomainJUnitTest {
    @Test
    void shouldEmployeeAtITDepartment() {
        Department department = new Department();
        department.setName("IT");
        String name = department.getName();
        Employee employee = new Employee();
        employee.setDepartment(department);
    }

}
