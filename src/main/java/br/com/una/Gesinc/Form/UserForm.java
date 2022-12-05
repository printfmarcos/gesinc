package br.com.una.Gesinc.Form;

import br.com.una.Gesinc.Domain.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserForm {

    private String name;

    @NotEmpty @NotNull @Email(message = "Não é um email valido")
    private String email;

    @NotBlank(message = "{not.blank.password}")
    private String password;

    public Users convertToEntity() {
        return new Users(this.name, this.email, new BCryptPasswordEncoder().encode(this.password));
    }

    public Users update(Users users){

        users.setName(this.name);
        users.setEmail(this.email);
        users.setPassword(new BCryptPasswordEncoder().encode(this.password));
        return users;
    }
}
