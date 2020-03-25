package com.taskmanager.taskmanager.domain;


import com.taskmanager.taskmanager.domain.SubTask;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {

    @NotEmpty
    private String title;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String description;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime datetime;
    @NotNull
    @OneToMany
    private List<SubTask> subTasks;


    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }


    public Task() {
    }

    public Task(String name, String description, LocalDateTime datetime) {
        this.title = name;
        this.description = description;
        this.datetime = datetime;
        this.subTasks = new ArrayList<>();
    }

    public Task(@NotEmpty String title, @NotEmpty String description, @NotNull LocalDateTime datetime, @NotNull List<SubTask> subTasks) {
        setTitle(title);
        setDescription(description);
        setDatetime(datetime);
        this.subTasks = subTasks;
    }

    public void addSubtask(SubTask subTask) {
        subTasks.add(subTask);
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

}
