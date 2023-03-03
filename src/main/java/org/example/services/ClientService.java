package org.example.services;

import org.example.model.model1.Client;
import org.example.model.model1.Order;

import java.util.List;

public interface ClientService {

    Client getClientById(Integer id);
    void deleteByIdNotNull(Integer id);

}
