package br.com.una.Gesinc.Domain;

import br.com.una.Gesinc.Enum.IncidentType;
import br.com.una.Gesinc.Enum.Priority;
import br.com.una.Gesinc.Enum.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="TB_INCIDENT")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User requester;

    @ManyToOne
    private User attendant;

    @Column
    @Enumerated(EnumType.STRING)
    private IncidentType incidentType;

    @Column
    private String description;

    @Column
    private LocalDateTime openingDate;

    @Column
    private LocalDateTime closingDate;

    @Column
    private LocalDateTime updatedAt;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @Enumerated(EnumType.STRING)
    private Priority priority;

    public Incident(User requester, IncidentType incidentType, String description, LocalDateTime openingDate, Status status, Priority priority) {
        this.requester = requester;
        this.incidentType = incidentType;
        this.description = description;
        this.openingDate = openingDate;
        this.status = status;
        this.priority = priority;
    }

    public void closeIncident() {
        this.closingDate = LocalDateTime.now();
        this.status = Status.CONCLUDED;
    }

}
