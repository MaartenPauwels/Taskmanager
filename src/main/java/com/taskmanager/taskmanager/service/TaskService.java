package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.domain.SubTask;
import com.taskmanager.taskmanager.domain.Task;
import com.taskmanager.taskmanager.dto.SubTaskDTO;
import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.repository.SubTaskRepository;
import com.taskmanager.taskmanager.repository.TaskRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service

public class TaskService implements TaskServiceInterface {
    private TaskRepository taskRepository;
    private SubTaskRepository subTaskRepository;

    public TaskService(TaskRepository taskRepository, SubTaskRepository subTaskRepository) {
        this.taskRepository = taskRepository;
        this.subTaskRepository = subTaskRepository;
    }

    public void updateSubtask(SubTaskDTO subTaskDTO) {
        SubTask subTask = getSubTaskfromDTO(subTaskDTO);
        subTask.setId(subTask.getId());
        subTaskRepository.save(subTask);
    }


    @Override
    public List<TaskDTO> getTasks() {
        List<TaskDTO>nieuw= new ArrayList<>();
        for (Task task: taskRepository.findAll()) {
            nieuw.add(getTaskDTO(task));
        }
        return nieuw;
    }

    @Override
    public TaskDTO getTaskDTO(long id) {
        return getTaskDTO(taskRepository.getOne(id));
        //throw new IllegalArgumentException("id bestaat niet");
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        taskRepository.save(getTaskfromDTO(taskDTO));
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Task task = getTaskfromDTO(taskDTO);
        task.setId(taskDTO.getId());
        taskRepository.save(task);
    }

    @Override
    public void addSubTask(long id, SubTaskDTO subtaskDTO) {
        TaskDTO taskDTO= getTaskDTO(taskRepository.getOne(id));
        taskDTO.addSubtask(subtaskDTO);
        Task task = getTaskfromDTO(taskDTO);
        task.setId(id);
        taskRepository.save(task);
    }

    private Task getTaskfromDTO(TaskDTO taskDTO){
            return new Task(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDatetime(), getSubtasksFromDTO(taskDTO.getSubTasks()));

    }

    private TaskDTO getTaskDTO(Task task) {
        return new TaskDTO(task.getTitle(),task.getDescription(),task.getDatetime(),getSubtaskDTOs(task.getSubTasks()),task.getId());
    }
    private SubTaskDTO getSubTaskDTO(SubTask subTask){
        return new SubTaskDTO(subTask.getTitle(), subTask.getDescription(),(subTask.getId()));
    }
    private SubTask getSubTaskfromDTO(SubTaskDTO subTask){
        return new SubTask(subTask.getTitle(), subTask.getDescription());
    }

    private List<SubTaskDTO> getSubtaskDTOs(List<SubTask>subTasks){
        List<SubTaskDTO> nieuw= new ArrayList<>();
        for (SubTask subtask:subTasks) {
            nieuw.add(getSubTaskDTO(subtask));

        }
        return nieuw;
    }

    private List<SubTask> getSubtasksFromDTO(List<SubTaskDTO>subTasks){
        List<SubTask> nieuw= new ArrayList<>();
        if(subTasks != null){
        for (SubTaskDTO subtask:subTasks) {
            nieuw.add(getSubTaskfromDTO(subtask));
        }
        }
        System.out.println("lijst met subtasks" + nieuw.size());
        return nieuw;
    }

}
