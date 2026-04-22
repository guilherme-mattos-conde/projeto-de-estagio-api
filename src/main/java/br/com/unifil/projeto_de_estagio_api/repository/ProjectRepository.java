package br.com.unifil.projeto_de_estagio_api.repository;

import br.com.unifil.projeto_de_estagio_api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
