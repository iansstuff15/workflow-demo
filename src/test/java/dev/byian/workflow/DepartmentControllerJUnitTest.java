package dev.byian.workflow;

import dev.byian.workflow.controller.DepartmentController;
import dev.byian.workflow.domain.Department;
import dev.byian.workflow.service.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerJUnitTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    DepartmentService departmentService;
    private ResponseEntity<Map<String, Object>> getMockResponseEntity() {
        return ResponseEntity.ok(Map.of("size", 1, "data", "mockedData"));
    }
    private ResponseEntity<Map<String,String>> getMockResponsentityString(){
        return ResponseEntity.ok(Map.of("size", "1", "data", "mockedData"));
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetAllDepartments() throws Exception {
        PageRequest pageRequest = PageRequest.of(1, 10);
        Mockito.when(departmentService.getAllDepartments(pageRequest)).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/department/all?page=1&size=10")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetDepartmentById() throws Exception {
        Mockito.when(departmentService.getDepartmentById(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/department/find?id=e0defa9c-1389-46e6-a9c1-73aaa5fe53d1")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetDepartmentByName() throws Exception {
        Mockito.when(departmentService.getDepartmentByName(Mockito.anyString())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/department/find?name=IT")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testDeleteDepartment() throws Exception {
        Mockito.when(departmentService.deleteDepartment(Mockito.any())).thenReturn(getMockResponsentityString());
        MvcResult mvcResult = mockMvc.perform(delete("/api/department/delete")
                        .param("id","9c4a36b5-a3e3-445e-a60e-cd81405456d1")
                        .with(csrf()))
                .andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testAddDepartment() throws Exception {
        Mockito.when(departmentService.addDepartment(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(post("/api/department/add")
                        .content("{\"name\":\"Information Technology\"}")
                        .contentType("application/json")
                        .with(csrf()))
                .andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testUpdateDepartment() throws Exception {
        Mockito.when(departmentService.updateDepartment(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(put("/api/department/update")
                        .content("{\"id\":\"9c4a36b5-a3e3-445e-a60e-cd81405456d1\",\"name\":\"IT\"}")
                        .contentType("application/json")
                        .with(csrf()))
                .andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGenerateDepartments() throws Exception {
        Mockito.when(departmentService.generateFakeDepartment(Mockito.anyInt())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/department/generate")
                .param("count","10")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

}
