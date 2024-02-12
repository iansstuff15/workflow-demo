package dev.byian.workflow;

import dev.byian.workflow.controller.PayrollController;
import dev.byian.workflow.service.PayrollService;
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

@WebMvcTest(PayrollController.class)
public class PayrollControllerJUnitTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PayrollService payrollService;

    private ResponseEntity<Map<String, Object>> getMockResponseEntity() {
        return ResponseEntity.ok(Map.of("size", 1, "data", "mockedData"));
    }
    private ResponseEntity<Map<String,String>> getMockResponsentityString(){
        return ResponseEntity.ok(Map.of("size", "1", "data", "mockedData"));
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetAllPayrolls() throws Exception {
        PageRequest pageRequest = PageRequest.of(1, 10);

        Mockito.when(payrollService.getAllPayroll(pageRequest)).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/payroll/all?page=1&size=10")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetPayrollById() throws Exception {
        Mockito.when(payrollService.getPayrollById(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/payroll/find?id=e0defa9c-1389-46e6-a9c1-73aaa5fe53d1")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testAddPayroll() throws Exception {

        Mockito.when(payrollService.addPayroll(Mockito.any(), Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(post("/api/payroll/add")
                .param("employeeID","9c4a36b5-a3e3-445e-a60e-cd81405456d1")
                .content("{\"grossPay\":1000,\"netPay\":1000,\"deductions\":\"100\"}")
                .contentType("application/json")
                .with(csrf())).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testDeletePayroll() throws Exception {
        Mockito.when(payrollService.deletePayroll(Mockito.any())).thenReturn(getMockResponsentityString());
        MvcResult mvcResult = mockMvc.perform(delete("/api/payroll/delete")
                .param("id","9c4a36b5-a3e3-445e-a60e-cd81405456d1").with(csrf())).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testUpdateGrossPayPayroll() throws Exception {
        Mockito.when(payrollService.updatePayroll(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(put("/api/payroll/update")
                        .content("{\"grossPay\":1000,\"employeeID\":\"9c4a36b5-a3e3-445e-a60e-cd81405456d1\"}")
                .contentType("application/json")
                .with(csrf())).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testUpdateDeductionsPayroll() throws Exception {
        Mockito.when(payrollService.updatePayroll(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(put("/api/payroll/update")
                        .content("{\"deductions\":1000,\"employeeID\":\"9c4a36b5-a3e3-445e-a60e-cd81405456d1\"}")
                .contentType("application/json")
                .with(csrf())).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGeneratePayrolls() throws Exception {
        Mockito.when(payrollService.generateFakePayroll(Mockito.anyInt(), Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/payroll/generate")
                .param("count","20")
                .param("employeeID","425fa5ff-9a22-4779-aaa3-8f30e5fdc222")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }


}
