package br.com.una.Gesinc.Controller;

import br.com.una.Gesinc.Domain.Roles;
import br.com.una.Gesinc.Domain.Users;
import br.com.una.Gesinc.Dto.UserDto;
import br.com.una.Gesinc.Form.UserForm;
import br.com.una.Gesinc.Repository.IncidentRepository;
import br.com.una.Gesinc.Repository.RolesRepository;
import br.com.una.Gesinc.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping()
    public List<UserDto> getAllUser(){
        return UserDto.convertToDTO(userRepository.findAll());
    }

    @GetMapping("/{name}")
    public UserDto getUserByName(@PathVariable String name){
        Users user = userRepository.findTop1ByName(name);
        return new UserDto(user);
    }

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email){
        Users user = userRepository.findByEmail(email);
        return new UserDto(user);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity saveUser(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder){
        Optional<Roles> optionalRoles = rolesRepository.findById(1L);
        if(optionalRoles.isPresent()){
            Users users = userForm.convertToEntity();
            users.getRoles().add(optionalRoles.get());
            userRepository.save(users);

            URI uri = uriBuilder.path("/user/{id}").buildAndExpand(users.getId()).toUri();
            return ResponseEntity.created(uri).body(new UserDto(users));
        }
        return ResponseEntity.unprocessableEntity().body("Role pradão não encontrada. Erro no banco!");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserForm userForm, @PathVariable Long id){

        Optional<Users> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()){
            Users users = userForm.update(userOptional.get());
            userRepository.save(users);
            return ResponseEntity.ok(new UserDto(users));
        }
        return  ResponseEntity.notFound().build();
    }


    //PAra deletar primeiro tenho que ver se não tenho um incidente naquele usuario,
    // lista os chamados e troca pro usuario adm e depois exclui o usuario
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser (@PathVariable Long id, Long incidentId){

//        Boolean haveIncidents = incidentRepository.getById(incidentId).getStatus() == Status.CONCLUDED;
        Optional<Users> userOptional = userRepository.findById(id);
//        if (haveIncidents == true) {
//            return ResponseEntity.badRequest().body("You have incidents open on your profile");
//        }
        if(userOptional.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}