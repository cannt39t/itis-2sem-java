package com.solncev.service;

import com.solncev.dto.ClientResponseDto;
import com.solncev.dto.CreateClientRequestDto;
import com.solncev.dto.CreateUserRequestDto;
import com.solncev.dto.UserResponseDto;
import com.solncev.model.Client;

public interface ClientService {

    Client getClientById(Integer id);
    void deleteByIdNotNull(Integer id);
    ClientResponseDto create(CreateClientRequestDto userDto);
}
