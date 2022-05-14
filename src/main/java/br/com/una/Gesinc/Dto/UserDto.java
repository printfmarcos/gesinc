package br.com.una.Gesinc.Dto;

import br.com.una.Gesinc.Enum.UserType;
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

    private UserType userType;

    public UserDto(Long id, String name, String email, UserType userType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userType = userType;
    }
}
