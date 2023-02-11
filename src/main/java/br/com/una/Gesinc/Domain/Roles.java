package br.com.una.Gesinc.Domain;

import br.com.una.Gesinc.Enum.UserType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_role")
@Getter
@Setter
public class Roles implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserType roleName;

    @Override
    public String getAuthority() {
        return this.roleName.name();
    }
}
