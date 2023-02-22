package org.example.dto;

import lombok.Builder;
import lombok.Data;
import org.example.model.User;

import java.time.LocalDate;

@Builder
@Data

public class UserResponseDto {
    private Integer id;

    private String name;

    private String email;

    private LocalDate birth;

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .build();
    }
}