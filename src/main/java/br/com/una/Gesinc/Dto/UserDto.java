package br.com.una.Gesinc.Dto;

import br.com.una.Gesinc.Domain.Roles;
import br.com.una.Gesinc.Domain.Users;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private List<String> roles;

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDto(Users users) { // constructor de conversao
        this.name = users.getName();
        this.roles = users.getRoles().stream().map(Roles::getAuthority).toList();
    }
    public UserDto(Users users, Boolean islogin) {
        this.name = users.getName();
        this.roles = users.getRoles().stream().map(Roles::getAuthority).toList();
    }


    public static List<UserDto> convertToDTO(List<Users> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}
