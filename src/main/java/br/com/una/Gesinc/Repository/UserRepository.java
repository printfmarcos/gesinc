package br.com.una.Gesinc.Repository;

import br.com.una.Gesinc.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
