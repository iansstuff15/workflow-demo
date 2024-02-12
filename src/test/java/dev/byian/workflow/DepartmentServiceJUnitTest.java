package dev.byian.workflow;

import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

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
    @Test
    void shouldCreateDepartmentWithProvidedID() {
        Department department = new Department();
        department.setId(UUID.fromString("cdc85dcb-072d-490d-8a7a-9c6ff7937f8b"));
        String id = department.getId().toString();
        Assertions.assertEquals("cdc85dcb-072d-490d-8a7a-9c6ff7937f8b", id);
    }

    @Test
    void shouldUpdateDepartmentNameFromItToHr() {
        Department department = new Department();
        department.setName("IT");
        department.update(Department.builder().name("HR").build());
        String name = department.getName();
        Assertions.assertEquals("HR", name);
    }
    @Test
    void shouldUpdateDepartmentNameFromItToFinance() {
        Department department = new Department();
        department.setName("IT");
        department.update(Department.builder().name("Finance").build());
        String name = department.getName();
        Assertions.assertEquals("Finance", name);
    }
    @Test
    void shouldUpdateDepartmentNameFromItToMarketing() {
        Department department = new Department();
        department.setName("IT");
        department.update(Department.builder().name("Marketing").build());
        String name = department.getName();
        Assertions.assertEquals("Marketing", name);
    }
    @Test
    void shouldUpdateDepartmentNameFromItToSales() {
        Department department = new Department();
        department.setName("IT");
        department.update(Department.builder().name("Sales").build());
        String name = department.getName();
        Assertions.assertEquals("Sales", name);
    }
    @Test
    void departmentShouldHaveOneEmployees() {
        Department department = new Department();
        Employee employee = new Employee();
        department.setEmployees(List.of(employee));
        Assertions.assertEquals(1, department.getEmployees().size());
    }
    @Test
    void departmentShouldHaveFiveEmployees() {
        Department department = new Department();
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        Employee employee3 = new Employee();
        Employee employee4 = new Employee();
        Employee employee5 = new Employee();
        department.setEmployees(List.of(employee1, employee2, employee3, employee4, employee5));
        Assertions.assertEquals(5, department.getEmployees().size());
    }
}
