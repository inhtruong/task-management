package com.cg.taskmanagement.service.task.impl;

import com.cg.taskmanagement.dto.TaskDto;
import com.cg.taskmanagement.entity.Task;
import com.cg.taskmanagement.entity.enumration.TaskStatus;
import com.cg.taskmanagement.entity.enumration.TaskType;
import com.cg.taskmanagement.repository.TaskRepository;
import com.cg.taskmanagement.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * @return
     */
    @Override
    public Iterable<TaskDto> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::mapTaskToDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<TaskDto> findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));

        return Optional.of(mapTaskToDTO(task));
    }

    /**
     * @param taskDto
     * @return
     */
    @Override
    public TaskDto save(TaskDto taskDto) {
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void remove(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDto> getAllTasksForToday() {
        List<Task> taskList = taskRepository.findTasksFromToday();

        return taskList.stream()
                .map(this::mapTaskToDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param taskDto
     * @return
     */
    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = mapDTOToTask(taskDto);

        // xử lý riêng cho status và type
        task.setStatus(TaskStatus.TODO);
        LocalDate localDate = LocalDateTime.parse(taskDto.getStart()).toLocalDate();
        TaskType taskType = LocalDate.now().equals(localDate) ? TaskType.DAILY : TaskType.NONE_DAILY;
        task.setType(taskType);

        Task savedTask = taskRepository.save(task);
        return mapTaskToDTO(savedTask);
    }

    /**
     * @param taskDto
     * @return
     */
    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        Task existingTask = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new NoSuchElementException("Task not found"));

        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setStart(LocalDateTime.parse(taskDto.getStart()));
        existingTask.setEnd(LocalDateTime.parse(taskDto.getEnd()));
        existingTask.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
        existingTask.setType(TaskType.valueOf(taskDto.getType()));

        Task updatedTask = taskRepository.save(existingTask);
        return mapTaskToDTO(updatedTask);
    }

    private TaskDto mapTaskToDTO(Task task) {
        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStart(task.getStart().toString());
        taskDto.setEnd(task.getEnd().toString());
        taskDto.setStatus(task.getStatus().toString());
        taskDto.setType(task.getType().toString());

        return taskDto;
    }

    private Task mapDTOToTask(TaskDto taskDto) {
        Task task = new Task();

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStart(LocalDateTime.parse(taskDto.getStart()));
        task.setEnd(LocalDateTime.parse(taskDto.getEnd()));

        return task;
    }
}
