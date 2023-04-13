package com.solncev.service;

import com.solncev.dto.ClientResponseDto;
import com.solncev.dto.CreateClientRequestDto;
import com.solncev.dto.CreateUserRequestDto;
import com.solncev.dto.UserResponseDto;
import com.solncev.model.Client;
import org.springframework.data.jpa.repository.Query;

import java.util.Queue;

public interface ClientService {

    Client getClientById(Integer id);
    void deleteByIdNotNull(Integer id);
    ClientResponseDto create(CreateClientRequestDto userDto, String url);
    Client getClientByName(String name);

    boolean verify(String verificationCode);
    void sendVerificationMail(String mail, String name, String code, String url);
}
