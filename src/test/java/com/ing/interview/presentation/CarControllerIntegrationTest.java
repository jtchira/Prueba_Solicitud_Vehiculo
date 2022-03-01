package com.ing.interview.presentation;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ing.interview.InterviewApplication;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InterviewApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenCarWhenCreateThenReturnIsOk() throws Exception {
        // given: Request Car
        JSONObject car = new JSONObject();
        car.put("age", 20);
        car.put("color", "YELLOW");
        car.put("model", "FIAT");

        this.mockMvc
            .perform(
                post("/car")
                    .contentType(APPLICATION_JSON)
                    .content(car.toString()))
            .andDo(print())
            // Response Status
            .andExpect(status().is(201))
            .andExpect(content().contentType(APPLICATION_JSON))
            // Response Body
            .andExpect(jsonPath("$.model").value("FIAT"))
            .andExpect(jsonPath("$.orderDate").exists());
    }

    @Test
    void givenCarDefaultColorWhenCreateThenReturnIsOk() throws Exception {
        // given: Request Car
        JSONObject car = new JSONObject();
        car.put("age", 20);
        car.put("model", "FIAT");

        this.mockMvc
                .perform(
                        post("/car")
                                .contentType(APPLICATION_JSON)
                                .content(car.toString()))
                .andDo(print())
                // Response Status
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                // Response Body
                .andExpect(jsonPath("$.model").value("FIAT"))
                .andExpect(jsonPath("$.orderDate").exists());
    }

    @Test
    void givenCarWhenCreateThenReturnStockException() throws Exception {
        // given: Request Car
        JSONObject car = new JSONObject();
        car.put("age", 20);
        car.put("color", "RED");
        car.put("model", "FIAT");

        this.mockMvc
                .perform(
                        post("/car")
                                .contentType(APPLICATION_JSON)
                                .content(car.toString()))
                .andDo(print())
                // Response Status
                .andExpect(status().isNotFound());
    }

    @Test
    void givenCarWhenCreateThenReturnInsuranceException() throws Exception {
        // given: Request Car
        JSONObject car = new JSONObject();
        car.put("age", 18);
        car.put("color", "YELLOW");
        car.put("model", "FIAT");

        this.mockMvc
                .perform(
                        post("/car")
                                .contentType(APPLICATION_JSON)
                                .content(car.toString()))
                .andDo(print())
                // Response Status
                .andExpect(status().isBadRequest());
    }

}