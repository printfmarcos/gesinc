package br.com.una.Gesinc.Dto;

import br.com.una.Gesinc.Domain.Action;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ActionDto {

    private Long id;
    private String description;
    private LocalDateTime actionDate;
    private String userName;
    private Boolean solution;

    public ActionDto(Long id, String description, LocalDateTime actionDate, String userName, Boolean solution) {
        this.id = id;
        this.description = description;
        this.actionDate = actionDate;
        this.userName = userName;
        this.solution = solution;
    }

    public ActionDto() {
    }

    public ActionDto(Action action) {
        this.id = action.getId();
        this.description = action.getDescription();
        this.actionDate = action.getActionDate();
        this.userName = action.getUsers().getName();
        this.solution = action.getSolution();
    }
}
