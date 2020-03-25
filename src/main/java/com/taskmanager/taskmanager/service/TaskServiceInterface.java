package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.SubTaskDTO;
import com.taskmanager.taskmanager.dto.TaskDTO;

import java.util.List;

public interface TaskServiceInterface {
    List<TaskDTO> getTasks();
    TaskDTO getTaskDTO(long id);
    void addTask(TaskDTO taskDTO);
    void updateTask(TaskDTO taskDTO);
    void addSubTask(long id, SubTaskDTO taskDTO);
}
