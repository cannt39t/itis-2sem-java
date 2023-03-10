package com.solncev.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CreateClientRequestDto {

    @NotBlank(message = "Name shouldn't be blank")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @NotBlank(message = "Email shouldn't be blank")
    private String email;

    @Size(min = 8, max = 63, message = "Password should contains from 8 to 63 symbols")
    private String password;

}
