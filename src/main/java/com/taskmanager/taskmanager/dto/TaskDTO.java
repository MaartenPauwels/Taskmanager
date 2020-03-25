package com.taskmanager.taskmanager.dto;

import com.taskmanager.taskmanager.domain.SubTask;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDTO {

    @NotEmpty
    private String title;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String description;
    @NotNull
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime datetime;
    private List<SubTaskDTO> subTasks;


    public TaskDTO() {
        subTasks= new ArrayList<>();
    }

    public TaskDTO(@NotEmpty String title, @NotEmpty String description, @NotNull LocalDateTime datetime, @NotNull List<SubTaskDTO> subTasks, long id) {
        this.title = title;
        this.description = description;
        this.datetime = datetime;
        this.subTasks = subTasks;
        this.id= id;
    }

    public void addSubtask(SubTaskDTO subTaskDTO){
        subTasks.add(subTaskDTO);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public List<SubTaskDTO> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskDTO> subTasks) {
        this.subTasks = subTasks;
    }

}
