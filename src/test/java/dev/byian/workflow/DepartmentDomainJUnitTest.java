package dev.byian.workflow.domain;

import dev.byian.workflow.domain.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DepartmentDomainJUnitTest {
    @Test
    void shouldCreateITDepartment() {
        Department department = new Department();
        department.setName("IT");
        String name = department.getName();
        Assertions.assertEquals("IT", name);
    }
    @Test
    void shouldCreateHRDepartment() {
        Department department = new Department();
        department.setName("HR");
        String name = department.getName();
        Assertions.assertEquals("HR", name);
    }
    @Test
    void shouldCreateFinanceDepartment() {
        Department department = new Department();
        department.setName("Finance");
        String name = department.getName();
        Assertions.assertEquals("Finance", name);
    }
    @Test
    void shouldCreateMarketingDepartment() {
        Department department = new Department();
        department.setName("Marketing");
        String name = department.getName();
        Assertions.assertEquals("Marketing", name);
    }
    @Test
    void shouldCreateSalesDepartment() {
        Department department = new Department();
        department.setName("Sales");
        String name = department.getName();
        Assertions.assertEquals("Sales", name);
    }
}
