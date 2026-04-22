package br.com.unifil.projeto_de_estagio_api.service;

import br.com.unifil.projeto_de_estagio_api.dto.ProjectRequest;
import br.com.unifil.projeto_de_estagio_api.dto.ProjectResponse;
import br.com.unifil.projeto_de_estagio_api.dto.ProjectStatusUpdate;
import br.com.unifil.projeto_de_estagio_api.entity.Project;
import br.com.unifil.projeto_de_estagio_api.entity.ProjectStatus;
import br.com.unifil.projeto_de_estagio_api.exception.DuplicateResourceException;
import br.com.unifil.projeto_de_estagio_api.exception.ResourceNotFoundException;
import br.com.unifil.projeto_de_estagio_api.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
                .createdAt(LocalDateTime.now())
                .sheetsCount(0)
                .status(ProjectStatus.IN_PROGRESS)
                .build();

        if (repository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Project with this name already exists");
        }

        return mapToResponse(repository.save(project));
    }

    @Override
    public List<ProjectResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProjectResponse findById(Long id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        return mapToResponse(project);
    }

    @Override
    public ProjectResponse update(Long id, ProjectRequest request) {

        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        project.setName(request.getName());
        project.setClientName(request.getClientName());
        project.setCity(request.getCity());
        project.setState(request.getState());

        if (repository.existsByNameAndIdNot(request.getName(), id)) {
            throw new DuplicateResourceException("Project name already exists");
        }

        return mapToResponse(repository.save(project));
    }

    @Override
    public ProjectResponse updateStatus(Long id, ProjectStatusUpdate request) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        project.setStatus(request.getStatus());

        return mapToResponse(repository.save(project));
    }

    @Override
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found");
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
                .sheetsCount(project.getSheetsCount())
                .status(project.getStatus())
                .build();
    }
}
