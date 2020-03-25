package com.taskmanager.taskmanager.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class SubTaskDTO {

    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubTaskDTO() {
    }

    public SubTaskDTO(@NotEmpty String title, @NotEmpty String description, Long id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
