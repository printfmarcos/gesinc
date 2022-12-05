package br.com.una.Gesinc.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_action")
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
    private Users users;

    private Boolean solution;

    public Action(String description, Incident incident, LocalDateTime actionDate, Users users, Boolean solution) {
        this.description = description;
        this.incident = incident;
        this.actionDate = actionDate;
        this.users = users;
        this.solution = solution;
    }

    public Action() {
    }
}
