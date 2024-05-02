package be.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCalculatePlus() throws Exception {
        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operator\":\"plus\",\"left\":5,\"right\":7}"))
                .andExpect(status().isOk())
                .andExpect(content().string("5+7=12"));
    }

    @Test
    public void testCalculateMinus() throws Exception {
        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operator\":\"minus\",\"left\":5,\"right\":7}"))
                .andExpect(status().isOk())
                .andExpect(content().string("5-7=-2"));
    }

    @Test
    public void testCalculateMultiply() throws Exception {
        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operator\":\"multiply\",\"left\":5,\"right\":7}"))
                .andExpect(status().isOk())
                .andExpect(content().string("5*7=35"));
    }

    @Test
    public void testCalculateDivide() throws Exception {
        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operator\":\"divide\",\"left\":5,\"right\":7}"))
                .andExpect(status().isOk())
                .andExpect(content().string("5/7=0"));
    }

    @Test
    public void testCalculateDivideByZero() throws Exception {
        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operator\":\"divide\",\"left\":5,\"right\":0}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Division by zero isn't a valid action"));
    }
}