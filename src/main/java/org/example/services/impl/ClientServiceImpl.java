package org.example.services.impl;


import lombok.AllArgsConstructor;
import org.example.model.model1.Client;
import org.example.repository.repository1.ClientRepository;
import org.example.services.ClientService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client getClientById(Integer id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public void deleteByIdNotNull(Integer id) {
        clientRepository.deleteByIdNotNull(id);
    }
}
