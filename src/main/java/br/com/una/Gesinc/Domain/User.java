package br.com.una.Gesinc.Domain;

import br.com.una.Gesinc.Enum.TypeUser;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="TB_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;
}
