package com.rodrigoramos.tech_challenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        Long id,
        @NotBlank(message = "Campo requerido")
        String street,
        @NotBlank(message = "Campo requerido")
        String number,
        @NotBlank(message = "Campo requerido")
        String city,
        @NotBlank(message = "Campo requerido")
        String neighborhood,
        @NotBlank(message = "Campo requerido")
        String state,
        @NotBlank(message = "Campo requerido")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O código postal deve estar no formato 12345-678")
        String postalCode
) {

}
