package com.juggernauts.todoapp.models;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;

    @Column(nullable = false, columnDefinition = "VARCHAR(32)", name = "name")
    private String name;

    @Column(name = "is_done", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDone;

    @Column(name = "complete_by", columnDefinition = "DATE")
    private Date completeBy;

    @Column(length=256, nullable=false, unique=true)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private int categoryId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
