package br.com.una.Gesinc.Form;

import br.com.una.Gesinc.Domain.User;
import br.com.una.Gesinc.Enum.UserType;
import br.com.una.Gesinc.Repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
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

    private UserType userType;

    // transformar o form em entidade
    public User convertToEntity() {
        return new User(this.name, this.email, this.password, this.userType);
    }


    public User update(User user){

        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setUserType(this.userType);
        return user;
    }
}
