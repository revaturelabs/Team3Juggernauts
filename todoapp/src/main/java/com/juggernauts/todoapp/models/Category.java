package com.juggernauts.todoapp.models;

import lombok.*;

import javax.persistence.*;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "INTEGER")
    private int categoryId;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(32) CHECK (name != 'Complete')")
    private String categoryName;

    // This is from a previous "version"
//    @Column(name = "user_id", nullable = false, columnDefinition = "INTEGER")
//    private int userId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "INTEGER")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "category")
    private List<Task> tasks;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}