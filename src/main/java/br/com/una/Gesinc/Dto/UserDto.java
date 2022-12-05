package br.com.una.Gesinc.Dto;

import br.com.una.Gesinc.Domain.Users;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    public UserDto(Users users) { // constructor de conversao
        this.id = users.getId();
        this.email = users.getEmail();
        this.name = users.getName();
    }

    public static List<UserDto> convertToDTO(List<Users> users) {
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
