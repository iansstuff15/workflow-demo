package dev.byian.workflow;

import dev.byian.workflow.controller.HomeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(HomeController.class)
public class HomeControllerJUnitTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "ian", password = "password", authorities = "admin")
    void testHomeWithAuthenticatedUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    void testHomeWithUnAuthenticatedUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/")).andReturn();
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), mvcResult.getResponse().getStatus());
    }
}
