package br.com.una.Gesinc.Dto;

import br.com.una.Gesinc.Domain.User;
import br.com.una.Gesinc.Enum.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {
    private Long id;

    private String name;

    private String email;

    public UserDto(Long id, String name, String email) { //constructor
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDto(User user) { // constructor de conversao
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }

    public static List<UserDto> convertToDTO(List<User> users) {
        //List<User> users = new ArrayList<>();
        //List<UserDto> usersDto = new ArrayList<>();
        //
        // for (int i = 0; i < users.size(); i++) {
        //          UserDto udto = new UserDto(users.get(i));
        //            usersDto.add(udto);
        //        }
        //
        //        //return usersDto;

        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}
