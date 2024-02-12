package dev.byian.workflow;

import dev.byian.workflow.controller.SupplierController;
import dev.byian.workflow.service.SupplierService;
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

@WebMvcTest(SupplierController.class)
public class SupplierControllerJUnitTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    SupplierService supplierService;

    private ResponseEntity<Map<String, Object>> getMockResponseEntity() {
        return ResponseEntity.ok(Map.of("size", 1, "data", "mockedData"));
    }
    private ResponseEntity<Map<String,String>> getMockResponsentityString(){
        return ResponseEntity.ok(Map.of("size", "1", "data", "mockedData"));
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetAllSuppliers() throws Exception {
        PageRequest pageRequest = PageRequest.of(1, 10);

        Mockito.when(supplierService.getAllSupplier(pageRequest)).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/supplier/all?page=1&size=10")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetSupplierById() throws Exception {
        Mockito.when(supplierService.getSupplierById(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/supplier/find?id=e0defa9c-1389-46e6-a9c1-73aaa5fe53d1")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetSupplierByName() throws Exception {
        Mockito.when(supplierService.getSupplierByName(Mockito.anyString())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/supplier/find?name=John")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetSupplierByContactNumber() throws Exception {
        Mockito.when(supplierService.getSupplierByContactNumber(Mockito.anyString())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/supplier/find?contactNumber=1234567890")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetSupplierByAddress() throws Exception {
        Mockito.when(supplierService.getSupplierByAddress(Mockito.anyString())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/supplier/find?address=1234567890")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGetSupplierByEmail() throws Exception {
        Mockito.when(supplierService.getSupplierByEmail(Mockito.anyString())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/supplier/find?email=test@email.com")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testAddSupplier() throws Exception {

        Mockito.when(supplierService.addSupplier(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(post("/api/supplier/add")
                .content("{\"name\":\"apple\",\"address\":\"apple street\",\"contactNumber\":\"1234567890\",\"email\":\"apple@email.com\"}")
                .contentType("application/json")
                .with(csrf())).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testDeleteSupplier() throws Exception {
        Mockito.when(supplierService.deleteSupplier(Mockito.any())).thenReturn(getMockResponsentityString());
        MvcResult mvcResult = mockMvc.perform(delete("/api/supplier/delete")
                .param("id","9c4a36b5-a3e3-445e-a60e-cd81405456d1").with(csrf())).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testUpdateSupplier() throws Exception {
        Mockito.when(supplierService.updateSupplier(Mockito.any())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(put("/api/supplier/update")
                .content("{\"name\":\"apple\",\"address\":\"apple street\",\"contactNumber\":\"1234567890\",\"email\":\"apple@email.com\"}")
                .contentType("application/json")
                .with(csrf())).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testGenerateSuppliers() throws Exception {
        Mockito.when(supplierService.generateFakeSupplier(Mockito.anyInt())).thenReturn(getMockResponseEntity());
        MvcResult mvcResult = mockMvc.perform(get("/api/supplier/generate?count=1&departmentID=9c4a36b5-a3e3-445e-a60e-cd81405456d1")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }


}
