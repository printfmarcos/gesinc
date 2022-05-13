package br.com.una.Gesinc.Dto;

import br.com.una.Gesinc.Domain.Incident;
import br.com.una.Gesinc.Domain.User;
import br.com.una.Gesinc.Enum.Priority;
import br.com.una.Gesinc.Enum.Status;
import br.com.una.Gesinc.Repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class IncidentDto {

    private Long id;

    private UserDto requester;

    private UserDto attendant;

    private String description;

    private LocalDateTime openingDate;

    private LocalDateTime closingDate;

    private Status status;

    private Priority priority;

    public IncidentDto(Incident incident) {
        this.id = incident.getId();

        this.requester = new UserDto(incident.getRequester().getId(), incident.getRequester().getName(),
                incident.getRequester().getEmail(), incident.getRequester().getTypeUser());

        if (incident.getAttendant() != null){
            this.attendant = new UserDto(incident.getAttendant().getId(),incident.getAttendant().getName(),
                    incident.getAttendant().getEmail(), incident.getAttendant().getTypeUser());
        }

        this.description = incident.getDescription();
        this.openingDate = incident.getOpeningDate();
        this.status = incident.getStatus();
        this.priority = incident.getPriority();
        this.closingDate = incident.getClosingDate();
    }

    public static Page<IncidentDto> convertToDto(Page<Incident> incidents) {
        return incidents.map(IncidentDto::new);
    }
}
