package com.cg.taskmanagement.controller;

import com.cg.taskmanagement.dto.TaskDto;
import com.cg.taskmanagement.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public ModelAndView getTasksForToday() {
        ModelAndView modelAndView = new ModelAndView("tasks");
        modelAndView.addObject("tasks", taskService.getAllTasksForToday());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateTaskForm() {
        ModelAndView modelAndView = new ModelAndView("create-task");
        // Thêm các thông tin cần thiết vào model and view (nếu cần)
        modelAndView.addObject("taskDto", new TaskDto());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createTask(@ModelAttribute("taskDto") TaskDto taskDto) {
        // Xử lý lưu param dto vào model và save task
        TaskDto createTask = taskService.createTask(taskDto);
        ModelAndView modelAndView = new ModelAndView("redirect:/tasks");
        // Thêm các thông tin cần thiết vào model and view (nếu cần)
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateTaskForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("update-task");
        // Thêm các thông tin cần thiết vào model and view (nếu cần)
        modelAndView.addObject("taskDto", taskService.findById(id));
        return modelAndView;
    }

    @PutMapping("/update")
    public ModelAndView updateTask(@ModelAttribute("taskDto") TaskDto taskDto) {
        // Xử lý lưu param dto vào model và save task
        TaskDto updateTask = taskService.updateTask(taskDto);
        ModelAndView modelAndView = new ModelAndView("redirect:/tasks");
        // Thêm các thông tin cần thiết vào model and view (nếu cần)
        return modelAndView;
    }
}
