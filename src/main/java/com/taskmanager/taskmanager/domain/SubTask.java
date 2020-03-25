package com.taskmanager.taskmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
@Entity

public class SubTask {
    @NotEmpty
    @Id
    private String title;


    @NotEmpty
    private String description;
    private Long id;
    @ManyToOne
    private Task task;

    public SubTask(@NotEmpty String title, @NotEmpty String description) {
        setTitle(title);
        setDescription(description);
    }

    public SubTask() {
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
