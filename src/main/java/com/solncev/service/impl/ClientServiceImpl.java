package com.solncev.service.impl;


import com.solncev.dto.ClientResponseDto;
import com.solncev.dto.CreateClientRequestDto;
import com.solncev.model.Client;
import com.solncev.repository.ClientRepository;
import com.solncev.service.ClientService;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public Client getClientById(Integer id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public void deleteByIdNotNull(Integer id) {
        clientRepository.deleteByIdNotNull(id);
    }

    @Override
    public ClientResponseDto create(CreateClientRequestDto createClientRequestDto) {
        Client client = Client.builder()
                .name(createClientRequestDto.getName())
                .email(createClientRequestDto.getEmail())
                .birth(createClientRequestDto.getBirth())
                .password(encoder.encode(createClientRequestDto.getPassword()))
                .build();
        return ClientResponseDto.fromEntity(clientRepository.save(client));
    }
}
