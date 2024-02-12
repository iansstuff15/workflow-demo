package dev.byian.workflow;

import dev.byian.workflow.dao.DepartmentDao;
import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.Employee;
import dev.byian.workflow.service.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceJUnitTest {
   @Mock
   private DepartmentDao departmentDao;
   @InjectMocks
    private DepartmentService departmentService;
    @Test
    void testGetAllDepartments() {
        PageRequest pageRequest = PageRequest.of(1, 10);
        Page<Department> mockedPage = mock(Page.class);
        when(mockedPage.isEmpty()).thenReturn(false);
        when(departmentDao.findAll(pageRequest)).thenReturn(mockedPage);

        ResponseEntity<Map<String, Object>> response = departmentService.getAllDepartments(pageRequest);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull( response.getBody().get("size"));
        Assertions.assertNotNull(response.getBody().get("data"));
        verify(departmentDao, times(1)).findAll(pageRequest);
    }
    @Test
    void testAddDepartment() {
        Department department = new Department();
        when(departmentDao.save(department)).thenReturn(department);

        ResponseEntity<Map<String, Object>> response = departmentService.addDepartment(department);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Department " + department.getName() + " added successfully", response.getBody().get("message"));
        Assertions.assertNotNull(response.getBody().get("data"));
        verify(departmentDao, times(1)).save(department);
    }

    @Test
    void testDeleteDepartment() {

        UUID id = UUID.randomUUID();

        ResponseEntity<Map<String, String>> response = departmentService.deleteDepartment(id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Department deleted successfully with ID of " + id, response.getBody().get("message"));
        verify(departmentDao, times(1)).deleteById(id);
    }

    @Test
    void testUpdateDepartment() {

        Department department = new Department();
        department.setId(UUID.randomUUID());
        when(departmentDao.findById(department.getId())).thenReturn(Optional.of(department));
        when(departmentDao.save(department)).thenReturn(department);

        ResponseEntity<Map<String, Object>> response = departmentService.updateDepartment(department);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Department updated successfully with ID of " + department.getId(), response.getBody().get("message"));
        Assertions.assertNotNull(response.getBody().get("data"));
        verify(departmentDao, times(1)).findById(department.getId());
        verify(departmentDao, times(1)).save(department);
    }

    @Test
    void testUpdateDepartmentNotFound() {
        Department department = new Department();
        department.setId(UUID.randomUUID());
        when(departmentDao.findById(department.getId())).thenReturn(Optional.empty());
        ResponseEntity<Map<String, Object>> response = departmentService.updateDepartment(department);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("No department found with ID " + department.getId(), response.getBody().get("message"));
        verify(departmentDao, times(1)).findById(department.getId());
        verify(departmentDao, never()).save(department);
    }

    @Test
    void testGetDepartmentByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(departmentDao.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Map<String, Object>> response = departmentService.getDepartmentById(id);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("No department found with ID " + id, response.getBody().get("message"));
        verify(departmentDao, times(1)).findById(id);
    }
}
