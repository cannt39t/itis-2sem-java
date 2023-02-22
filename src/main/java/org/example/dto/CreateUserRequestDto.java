package org.example.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class CreateUserRequestDto {

    @NotBlank(message = "Name shouldn't be blank")
    @Size(max = 50, message = "Name should be less than 50 characters")
    private String name;

    @NotBlank(message = "Email shouldn't be blank")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
    private String email;

    @Past
    @NotNull(message = "Birthdate shouldn't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
}
