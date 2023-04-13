package com.solncev.service;


import com.solncev.Application;
import com.solncev.config.MailConfig;
import com.solncev.model.Client;
import com.solncev.repository.ClientRepository;
import com.solncev.repository.ProductRepository;
import com.solncev.service.impl.ClientServiceImpl;
import com.solncev.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(ClientServiceImpl.class)
@ContextConfiguration(classes = Application.class)
public class ClientServiceUnitTest {

    @Autowired
    private ClientServiceImpl clientService;
    @MockBean
    private PasswordEncoder encoder;
    @MockBean
    private JavaMailSender jms;
    @MockBean
    private MailConfig mc;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(clientService).isNotNull();
    }


    @Test
    public void testFindClientByName_thenReturningClient() {
        int clientID = 20;
        clientService.getClientById(clientID);
        verify(this.clientRepository, times(1)).getClientById(clientID);
    }

    @Test
    public void testDeleteClientById() throws Exception {
        int clientId = 1;
        clientService.deleteByIdNotNull(clientId);
        verify(this.clientRepository, times(1)).deleteByIdNotNull(clientId);
    }

}
