package br.com.una.Gesinc.Repository;

import br.com.una.Gesinc.Domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {

    List<Action> findByIncidentId(Long id);
}
