package br.com.una.Gesinc.Repository;

import br.com.una.Gesinc.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findTop1ByName(String name);

    User findByName(String name);

    Optional<User> findByUsername(String username);
}
