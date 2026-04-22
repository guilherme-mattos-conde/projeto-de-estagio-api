package br.com.unifil.projeto_de_estagio_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ProjectRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2, message = "State must have 2 characters")
    private String state;
}
