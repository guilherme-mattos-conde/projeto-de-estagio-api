package br.com.unifil.projeto_de_estagio_api.dto;

import br.com.unifil.projeto_de_estagio_api.entity.ProjectStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ProjectStatusUpdate {

    @NotNull(message = "Status is required")
    private ProjectStatus status;
}
