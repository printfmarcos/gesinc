package br.com.una.Gesinc.Dto;

import br.com.una.Gesinc.Domain.Incident;
import br.com.una.Gesinc.Domain.User;
import br.com.una.Gesinc.Enum.Priority;
import br.com.una.Gesinc.Enum.Status;
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

    private long id;

    @NotNull
    private User requester;

    @NotNull
    private User attendant;

    private String description;

    private LocalDateTime openingDate;

    private LocalDateTime closingDate;

    private Status status;

    @NotNull
    private Priority priority;

    public IncidentDto(Incident incident) {
        this.id = incident.getId();
        this.requester = incident.getRequester();
        this.attendant = incident.getAttendant();
        this.description = incident.getDescription();
        this.openingDate = incident.getOpeningDate();
        this.closingDate = incident.getClosingDate();
        this.status = incident.getStatus();
        this.priority = incident.getPriority();
    }

    public static Page<IncidentDto> convert(Page<Incident> incidents) {
        return incidents.map(IncidentDto::new);
    }
}
