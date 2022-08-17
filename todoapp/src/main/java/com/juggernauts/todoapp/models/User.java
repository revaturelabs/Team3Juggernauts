package com.juggernauts.todoapp.models;

import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(nullable = false, columnDefinition = "VARCHAR(256)")
    private String password;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(256)")
    private String email;

    @Column(name = "email_verified", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean emailVerified;

    public User(int id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Category.class)
    @JoinColumn(name="category_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Category> categories;
}