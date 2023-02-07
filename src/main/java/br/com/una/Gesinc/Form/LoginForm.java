package br.com.una.Gesinc.Form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class LoginForm {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
