package com.cg.taskmanagement.dto;

import com.cg.taskmanagement.entity.Task;
import com.cg.taskmanagement.entity.enumration.TaskStatus;
import com.cg.taskmanagement.entity.enumration.TaskType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;

    private String title;

    private String description;

    private String start;

    private String end;

    private String status;

    private String type;

}
