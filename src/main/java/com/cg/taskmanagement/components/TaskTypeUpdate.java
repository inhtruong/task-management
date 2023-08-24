package com.cg.taskmanagement.components;

import com.cg.taskmanagement.entity.Task;
import com.cg.taskmanagement.entity.enumration.TaskType;
import com.cg.taskmanagement.repository.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

public class TaskTypeUpdate {
    private final TaskRepository taskRepository;

    private final String DAILY = "DAILY";
    private final String NONE_DAILY = "NONE_DAILY";

    public TaskTypeUpdate(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Scheduled(cron = "0 0 8 * * *") // Chạy vào 8h hàng ngày
    public void updateTaskTypeAutomatically() {
        LocalDate currentDate = LocalDate.now();

        List<Task> tasks = taskRepository.findAll();

        for (Task task : tasks) {
            LocalDate taskStartDate = task.getStart().toLocalDate();
            LocalDate taskEndDate = task.getEnd().toLocalDate();

            if (taskStartDate != null && taskEndDate != null) {
                if (currentDate.compareTo(taskStartDate) >= 0 && currentDate.compareTo(taskEndDate) <= 0) {
                    if (task.getType().equals(NONE_DAILY)) {
                        task.setType(TaskType.valueOf(DAILY));
                        taskRepository.save(task);
                    }
                } else {
                    if (task.getType().equals(DAILY)) {
                        task.setType(TaskType.valueOf(NONE_DAILY));
                        taskRepository.save(task);
                    }
                }
            }
        }
    }
}
