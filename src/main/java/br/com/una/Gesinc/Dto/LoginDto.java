package br.com.una.Gesinc.Dto;

import br.com.una.Gesinc.Domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    private String token;
    private UserDto user;

    public LoginDto(String token, Users user) {
        this.token = token;
        this.user = new UserDto(user, true);
    }
}
