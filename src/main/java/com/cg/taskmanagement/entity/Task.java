package com.cg.taskmanagement.entity;

import com.cg.taskmanagement.entity.enumration.TaskStatus;
import com.cg.taskmanagement.entity.enumration.TaskType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime start;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime end;

    @Enumerated(value = EnumType.STRING) // Sử dụng EnumType.STRING để ánh xạ tới tên giá trị enum
    @Column(columnDefinition = "enum('TODO', 'IN_PROGRESS', 'DISMISS', 'DONE')")
    private TaskStatus status;

    @Enumerated(value = EnumType.STRING) // Sử dụng EnumType.STRING để ánh xạ tới tên giá trị enum
    @Column(columnDefinition = "enum('DAILY', 'NONE_DAILY')")
    private TaskType type;

    @Column(columnDefinition = "BOOLEAN")
    private Boolean deleted;
}
