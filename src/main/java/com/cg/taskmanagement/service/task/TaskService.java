package com.cg.taskmanagement.service.task;

import com.cg.taskmanagement.dto.TaskDto;
import com.cg.taskmanagement.entity.Task;
import com.cg.taskmanagement.service.IGeneralService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService extends IGeneralService<TaskDto> {

    List<TaskDto> getAllTasksForToday();

    TaskDto createTask(TaskDto taskDto);

    TaskDto updateTask(TaskDto taskDto);

    void deleteTask(Long taskId);
}
