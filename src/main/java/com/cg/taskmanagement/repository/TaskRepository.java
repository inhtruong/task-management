package com.cg.taskmanagement.repository;

import com.cg.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE (DATE(t.start) >= DATE(CURRENT_TIMESTAMP) AND DATE(t.end) <= DATE(CURRENT_TIMESTAMP))\n" +
            "OR (DATE(t.start) >= DATE(CURRENT_TIMESTAMP) AND DATE(t.start) <= DATE(CURRENT_TIMESTAMP))\n" +
            "OR (DATE(t.end) >= DATE(CURRENT_TIMESTAMP) AND DATE(t.end) <= DATE(CURRENT_TIMESTAMP))\n" +
            "OR (DATE(t.start) <= DATE(CURRENT_TIMESTAMP) AND DATE(t.end) >= DATE(CURRENT_TIMESTAMP))\n" +
            "ORDER BY t.id DESC")
    List<Task> findTasksFromToday();

}
