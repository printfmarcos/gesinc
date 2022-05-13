package br.com.una.Gesinc.Dto;

import br.com.una.Gesinc.Enum.TypeUser;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private TypeUser typeUser;

    public UserDto(Long id, String name, String email, TypeUser typeUser) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.typeUser = typeUser;
    }
}
