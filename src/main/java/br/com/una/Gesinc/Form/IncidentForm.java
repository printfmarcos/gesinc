package br.com.una.Gesinc.Form;

import br.com.una.Gesinc.Domain.Incident;
import br.com.una.Gesinc.Domain.User;
import br.com.una.Gesinc.Enum.IncidentType;
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

    private IncidentType incidentType;

    private String description;

    private Priority priority;

    public Incident convertToEntity(UserRepository userRepository){
        User requester = userRepository.getById(this.requester);

        return new Incident(requester, this.incidentType, this.description, LocalDateTime.now(), Status.OPENED, this.priority);
    }

    public Incident update(Incident incident, UserRepository userRepository){

        User attendant = userRepository.getById(this.attendant);

        incident.setDescription(this.description);
        incident.setPriority(this.priority);
        incident.setStatus(Status.INPROGRESS);
        incident.setAttendant(attendant);
        incident.setUpdatedAt(LocalDateTime.now());

        return incident;
    }
}
