package br.com.unifil.projeto_de_estagio_api.dto;

import br.com.unifil.projeto_de_estagio_api.entity.ProjectStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProjectResponse {

    private Long id;
    private String name;
    private String clientName;
    private String city;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer sheetsCount;
    private ProjectStatus status;
}
