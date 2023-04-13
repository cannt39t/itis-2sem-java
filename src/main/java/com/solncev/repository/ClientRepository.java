package com.solncev.repository;

import com.solncev.dto.ClientResponseDto;
import com.solncev.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client getClientById(Integer id);

    Client findByEmail(String email);

    @Modifying
    @Query("DELETE FROM Client c WHERE c.id = :id")
    void deleteByIdNotNull(@Param("id") Integer id);

    Client findByVerificationCode(String code);
}
