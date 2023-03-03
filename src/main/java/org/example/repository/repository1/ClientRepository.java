package org.example.repository.repository1;

import org.example.model.User;
import org.example.model.model1.Client;
import org.example.model.model1.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client getClientById(Integer id);
    void deleteByIdNotNull(Integer id);

}
