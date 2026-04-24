package br.com.unifil.projeto_de_estagio_api.service;

import br.com.unifil.projeto_de_estagio_api.dto.ProjectRequest;
import br.com.unifil.projeto_de_estagio_api.dto.ProjectResponse;
import br.com.unifil.projeto_de_estagio_api.dto.ProjectStatusUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    ProjectResponse create(ProjectRequest request);

    Page<ProjectResponse> findAll(Pageable pageable);

    ProjectResponse findById(Long id);

    ProjectResponse update(Long id, ProjectRequest request);

    ProjectResponse updateStatus(Long id, ProjectStatusUpdate status);

    void delete(Long id);
}
