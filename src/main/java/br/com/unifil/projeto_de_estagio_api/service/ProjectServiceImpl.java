package br.com.unifil.projeto_de_estagio_api.service;

import br.com.unifil.projeto_de_estagio_api.dto.ProjectRequest;
import br.com.unifil.projeto_de_estagio_api.dto.ProjectResponse;
import br.com.unifil.projeto_de_estagio_api.dto.ProjectStatusUpdate;
import br.com.unifil.projeto_de_estagio_api.entity.Project;
import br.com.unifil.projeto_de_estagio_api.exception.DuplicateResourceException;
import br.com.unifil.projeto_de_estagio_api.exception.ResourceNotFoundException;
import br.com.unifil.projeto_de_estagio_api.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    @Override
    public ProjectResponse create(ProjectRequest request) {

        Project project = Project.builder()
                .name(request.getName())
                .clientName(request.getClientName())
                .city(request.getCity())
                .state(request.getState())
                .build();

        if (repository.existsByNameAndClientNameAndCityAndState(
                request.getName(),
                request.getClientName(),
                request.getCity(),
                request.getState()
        )) {
            throw new DuplicateResourceException("Já existe um projeto com essas informações");
        }

        return mapToResponse(repository.save(project));
    }

    @Override
    public Page<ProjectResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public ProjectResponse findById(Long id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado"));

        return mapToResponse(project);
    }

    @Override
    public ProjectResponse update(Long id, ProjectRequest request) {

        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado"));

        project.setName(request.getName());
        project.setClientName(request.getClientName());
        project.setCity(request.getCity());
        project.setState(request.getState());

        if (repository.existsByNameAndClientNameAndCityAndStateAndIdNot(
                request.getName(),
                request.getClientName(),
                request.getCity(),
                request.getState(),
                id
        )) {
            throw new DuplicateResourceException("Já existe um projeto com essas informações");
        }

        return mapToResponse(repository.save(project));
    }

    @Override
    public ProjectResponse updateStatus(Long id, ProjectStatusUpdate request) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado"));

        project.setStatus(request.getStatus());

        return mapToResponse(repository.save(project));
    }

    @Override
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Projeto não encontrado");
        }

        repository.deleteById(id);
    }

    private ProjectResponse mapToResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .clientName(project.getClientName())
                .city(project.getCity())
                .state(project.getState())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .sheetsCount(project.getSheetsCount())
                .status(project.getStatus())
                .build();
    }
}
