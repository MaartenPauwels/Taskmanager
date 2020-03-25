package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.SubTaskDTO;
import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.domain.Task;
import com.taskmanager.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskConstroller {
    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable String id) {
        try {
            model.addAttribute("task", taskService.getTaskDTO(Long.parseLong(id)));
        } catch (IllegalArgumentException ex) {
            return "detail";
        }

        return "detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable String id) {
        try {
            //model.addAttribute("task", service.getTask(Integer.parseInt(id)));
            model.addAttribute("task", taskService.getTaskDTO(Long.parseLong(id)));

        } catch (IllegalArgumentException ex) {
            return "detail";
        }

        return "edit";
    }

    @GetMapping("/{id}/sub/create")
    public String createSubtask(Model model,@PathVariable String id) {
        SubTaskDTO subTask= new SubTaskDTO();
        subTask.setId(Long.valueOf(id));
        model.addAttribute("subtask", subTask);

        return "addSubtask";
    }
    @PostMapping("/sub/create")
    public String AddSubtask(@ModelAttribute @Valid SubTaskDTO subTaskDTO, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "addSubtask";
        }
        System.out.println( "id = " + subTaskDTO.getId());
        taskService.updateSubtask(subTaskDTO);
        TaskDTO taskDTO = taskService.getTaskDTO(subTaskDTO.getId());
        taskDTO.addSubtask(subTaskDTO);
        taskService.updateTask(taskDTO);
       return "redirect:/tasks/"; //+task.getId();
    }


    @PostMapping("/update")
    public String update(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addTask";
        }
        taskService.updateTask(taskDTO);
        return "redirect:/tasks/"+taskDTO.getId();
    }


    @GetMapping("/new")
    public String addTask(Model model) {
        model.addAttribute("task", new TaskDTO());
        return "addTask";
    }

    @PostMapping("/addTask")
    public String createTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addTask";
        }
        taskService.addTask(taskDTO);
        return "redirect:/tasks/";
    }


    @GetMapping("/")
    public String index(Model model) {
        //System.out.println(service.getTaken());

        //model.addAttribute("Tasks", service.getTaken());
        model.addAttribute("Tasks", taskService.getTasks());
        return "index";
    }

}
