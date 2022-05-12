package br.com.una.Gesinc.Repository;

import br.com.una.Gesinc.Domain.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Page<Incident> findByRequesterId(Long userId, Pageable pagination);
}
