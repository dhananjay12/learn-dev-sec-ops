package com.djcodes.devsecops;

import com.djcodes.devsecops.dto.EmployeeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Calendar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CrudServiceApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void restTemplatePost_201() throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.set(2000, 9, 12);
        EmployeeDTO employeeDTO = EmployeeDTO.builder().fname("firstName").lname("lastname").email("fisrt.last@mail.com").dob(cal).build();

        mvc.perform(MockMvcRequestBuilders
                        .post("/employee")
                        .content(asJsonString(employeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
