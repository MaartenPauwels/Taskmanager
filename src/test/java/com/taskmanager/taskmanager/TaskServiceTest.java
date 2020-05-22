package com.taskmanager.taskmanager;

import com.taskmanager.taskmanager.domain.SubTask;
import com.taskmanager.taskmanager.domain.Task;
import com.taskmanager.taskmanager.dto.SubTaskDTO;
import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.repository.SubTaskRepository;
import com.taskmanager.taskmanager.repository.TaskRepository;
import com.taskmanager.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskServiceTest {
     @Autowired
    private TaskService taskService;

    @Test
    @Transactional
    public void testgetTasks(){
        //setup
        TaskDTO taskDTO= new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setDatetime(LocalDateTime.now());
        taskDTO.setTitle("test");
        taskDTO.setDescription("test1");
        taskService.addTask(taskDTO);

        List<TaskDTO> taken = taskService.getTasks();
        assertNotNull(taken);
        assertFalse(taken.isEmpty());
        assertEquals(1, taken.size());
        TaskDTO taak = taken.get(0);
        assertNotNull(taak);
    }


    @Test
    @Transactional
    public void testGetTaskDTO(){
        //setup
        TaskDTO taskDTO= new TaskDTO();
        taskDTO.setDatetime(LocalDateTime.now());
        taskDTO.setTitle("test123");
        taskDTO.setDescription("test123");
        taskService.addTask(taskDTO);

        //eerste id waarin wordt opgeslagen
        TaskDTO test= taskService.getTaskDTO(taskService.getTasks().get(0).getId());
        assertNotNull(test);
        assertTrue(test.getDescription().equals(taskDTO.getDescription()));
        assertTrue(test.getTitle().equals(taskDTO.getTitle()));
        assertTrue(test.getDatetime()==taskDTO.getDatetime());
        //assertTrue(test.getSubTasks()==taskDTO.getSubTasks());
    }


    @Test
    @Transactional
    public void testAddSubtask(){
        TaskDTO taskDTO= new TaskDTO();
        taskDTO.setDatetime(LocalDateTime.now());
        taskDTO.setTitle("test");
        taskDTO.setDescription("test1");
        taskService.addTask(taskDTO);
        TaskDTO test= taskService.getTasks().get(0);


        SubTaskDTO subTaskDTO= new SubTaskDTO();
        subTaskDTO.setDescription("subtaskdescription");
        subTaskDTO.setTitle("subtasktitle");
        //subTaskDTO.setId(test.getId());
        taskService.updateSubtask(subTaskDTO);
        taskService.addSubTask(test.getId(),subTaskDTO);

        List<SubTaskDTO> subtasks= taskService.getTaskDTO(test.getId()).getSubTasks();

        assertNotNull(subtasks);
        assertFalse(subtasks.isEmpty());
        assertEquals(1, subtasks.size());
        SubTaskDTO subTaak = subtasks.get(0);
        assertNotNull(subTaak);





    }
}
