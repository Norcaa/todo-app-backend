package hu.unideb.inf.todoapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false, updatable = false)
    private int taskId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Date day;
    @Column(nullable = false)
    private Boolean reminder;
    @Column(name = "user_id", nullable = false, updatable = false)
    private int userId;
}