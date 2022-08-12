package com.juggernauts.todoapp.models;

import lombok.*;

import javax.persistence.*;

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


    @Column(name = "user_id", nullable = false, columnDefinition = "INTEGER")
    private int userId;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(32) CHECK (name != 'Complete')")
    private String categoryName;
}
