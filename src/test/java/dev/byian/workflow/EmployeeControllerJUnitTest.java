package dev.byian.workflow;

import dev.byian.workflow.controller.EmployeeController;
import dev.byian.workflow.domain.Employee;
import dev.byian.workflow.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerJUnitTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    EmployeeService employeeService;

    private ResponseEntity<Map<String, Object>> getMockResponseEntity() {
        return ResponseEntity.ok(Map.of("size", 1, "data", "mockedData"));
    }
    private ResponseEntity<Map<String,String>> getMockResponsentityString(){
        return ResponseEntity.ok(Map.of("size", "1", "data", "mockedData"));
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetAllEmployees() throws Exception {
        PageRequest pageRequest = PageRequest.of(1, 10);

        Mockito.when(employeeService.getAllEmployees(pageRequest)).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/employee/all?page=1&size=10")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetEmployeeById() throws Exception {
        Mockito.when(employeeService.getEmployeeById(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/employee/find?id=e0defa9c-1389-46e6-a9c1-73aaa5fe53d1")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetEmployeeByEmail() throws Exception {
        Mockito.when(employeeService.getEmployeeByEmail(Mockito.anyString())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/employee/find?email=test@email.com")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testAddEmployee() throws Exception {

        Mockito.when(employeeService.addEmployee(Mockito.any(), Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(post("/api/employee/add")
                .param("departmentID","9c4a36b5-a3e3-445e-a60e-cd81405456d1")
                .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"phone\":\"1234567890\",\"position\":\"Manager\",\"email\":\")\"}")
                .contentType("application/json")
                .with(csrf())).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testDeleteEmployee() throws Exception {
        Mockito.when(employeeService.deleteEmployee(Mockito.any())).thenReturn(getMockResponsentityString());
        MvcResult mvcResult = mockMvc.perform(delete("/api/employee/delete")
                .param("id","9c4a36b5-a3e3-445e-a60e-cd81405456d1").with(csrf())).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testUpdateEmployee() throws Exception {
        Mockito.when(employeeService.updateEmployee(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(put("/api/employee/update")
                .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"phone\":\"1234567890\",\"position\":\"Manager\",\"email\":\")\"}")
                .contentType("application/json")
                .with(csrf())).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGenerateEmployees() throws Exception {
        Mockito.when(employeeService.generateFakeEmployee(Mockito.anyInt(), Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/employee/generate?count=1&departmentID=9c4a36b5-a3e3-445e-a60e-cd81405456d1")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }


}
