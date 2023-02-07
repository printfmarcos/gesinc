package br.com.una.Gesinc.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    private String token;

    public LoginDto(String token) {
        this.token = token;
    }
}
