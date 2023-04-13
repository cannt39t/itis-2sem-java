package com.solncev.controller;


import com.solncev.Application;
import com.solncev.service.ClientService;
import com.solncev.service.impl.CategoryServiceImpl;
import com.solncev.service.impl.ClientServiceImpl;
import com.solncev.service.impl.OrderServiceImpl;
import com.solncev.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ShopController.class)
@ContextConfiguration(classes = Application.class)
public class ShopControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientServiceImpl clientService;

    @MockBean
    private CategoryServiceImpl categoryService;

    @MockBean
    private ProductServiceImpl productService;

    @MockBean
    private OrderServiceImpl orderService;


    @Test
    public void testSaveUser_thenStatusIsOk() throws Exception {
        mockMvc.perform(post("/client/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user("user").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().is(405));
    }
}
