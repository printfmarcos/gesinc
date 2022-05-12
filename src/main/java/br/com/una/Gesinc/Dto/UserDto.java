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

    private long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private TypeUser typeUser;
}
