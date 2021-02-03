package com.cursor.library.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class WelcomeControllerTest extends BaseControllerTest {

    @Test
    void test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/helloworld"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
