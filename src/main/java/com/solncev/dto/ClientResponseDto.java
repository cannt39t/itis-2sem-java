package com.solncev.dto;

import com.solncev.model.Client;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClientResponseDto {

    private Integer id;
    private String name;
    private String email;
    private LocalDate birth;

    public static ClientResponseDto fromEntity(Client client) {
        return ClientResponseDto.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .birth(client.getBirth())
                .build();
    }

}
