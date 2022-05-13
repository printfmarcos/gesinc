package br.com.una.Gesinc.Form;

import br.com.una.Gesinc.Domain.Incident;
import br.com.una.Gesinc.Domain.User;
import br.com.una.Gesinc.Enum.Priority;
import br.com.una.Gesinc.Enum.Status;
import br.com.una.Gesinc.Repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class IncidentForm {

    private Long requester;

    private Long attendant;

    private String description;

    private Priority priority;

    public Incident convertToEntity(UserRepository userRepository){

        User requester = userRepository.getById(this.requester);
        User attendant = userRepository.getById(this.attendant);

        System.out.println(requester.getName());
        System.out.println(attendant.getName());

        System.out.println("--------------- dados dos usuarios recuperados corretamete------------");

        return new Incident(requester,attendant,this.description, LocalDateTime.now(), Status.OPENED, this.priority);
    }
}
