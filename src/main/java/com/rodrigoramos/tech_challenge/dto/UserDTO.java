package com.rodrigoramos.tech_challenge.dto;

import com.rodrigoramos.tech_challenge.entities.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record UserDTO(
        Long id,
        @NotBlank(message = "Campo requerido")
        String name,
        @NotBlank(message = "Campo requerido")
        String login,
        @NotBlank(message = "Campo requerido")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String password,
        LocalDateTime lastModifiedAt,
        List<Address> addresses
) {

}
