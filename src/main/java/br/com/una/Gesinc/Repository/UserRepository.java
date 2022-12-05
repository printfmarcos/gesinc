package br.com.una.Gesinc.Repository;

import br.com.una.Gesinc.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    Users findTop1ByName(String name);

    Users findByName(String name);

    Optional<Users> findByUsername(String username);
}
