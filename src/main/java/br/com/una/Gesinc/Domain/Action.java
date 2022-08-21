package br.com.una.Gesinc.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    private Incident incident;

    private LocalDateTime actionDate;

    @ManyToOne
    private User user;

    private Boolean solution;

    public Action(String description, Incident incident, LocalDateTime actionDate, User user, Boolean solution) {
        this.description = description;
        this.incident = incident;
        this.actionDate = actionDate;
        this.user = user;
        this.solution = solution;
    }

    public Action() {
    }
}
