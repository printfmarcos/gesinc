package br.com.una.Gesinc.Domain;

import br.com.una.Gesinc.Enum.Priority;
import br.com.una.Gesinc.Enum.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="TB_INCIDENT")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User requester;

    @ManyToOne
    private User attendant;

    @Column
    private String description;

    @Column
    private LocalDateTime openingDate;

    @Column
    private LocalDateTime closingDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @Enumerated(EnumType.STRING)
    private Priority priority;
}
