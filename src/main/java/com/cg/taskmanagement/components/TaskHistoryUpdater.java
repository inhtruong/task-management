package com.cg.taskmanagement.components;

import com.cg.taskmanagement.entity.Task;
import com.cg.taskmanagement.entity.TaskHistory;
import com.cg.taskmanagement.repository.TaskHistoryRepository;
import com.cg.taskmanagement.repository.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TaskHistoryUpdater {
    private final TaskRepository taskRepository;
    private final TaskHistoryRepository taskHistoryRepository;

    public TaskHistoryUpdater(TaskRepository taskRepository, TaskHistoryRepository taskHistoryRepository) {
        this.taskRepository = taskRepository;
        this.taskHistoryRepository = taskHistoryRepository;
    }

    @Scheduled(cron = "0 0 8 * * *") // Chạy vào 8h hàng ngày
    public void saveTaskToHistory() {
        LocalDate currentDate = LocalDate.now();

        List<Task> tasks = taskRepository.findAll();

        for (Task task : tasks) {
            LocalDate taskDate = task.getEnd().toLocalDate();
            if (taskDate.isBefore(currentDate)) {
                TaskHistory taskHistory = new TaskHistory();
                taskHistory.setTask(task);
                taskHistory.setActivityTime(taskDate);
                taskHistoryRepository.save(taskHistory);
            }
        }
    }
}
