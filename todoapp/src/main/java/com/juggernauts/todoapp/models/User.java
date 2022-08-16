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

    // NON-RELATIONAL FIELDS
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

<<<<<<< HEAD
    public User(int id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
=======
    public User(String password, String email, boolean emailVerified) {
        this.password = password;
        this.email = email;
        this.emailVerified = emailVerified;
>>>>>>> abf1dbf2a9c227a2d89849f75bcb7c0e24a55563
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Category.class)
    @JoinColumn(name="category_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Category> categories;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private EmailVerification emailVerification;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(referencedColumnName = "group_id")
//    private Group group;
//    private GroupMember groupMember;

//    @OneToMany(cascade = CascadeType.MERGE,  mappedBy = "user")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<GroupMember> groupMember;
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "group_members",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id"))
//    private Set<Group> groups;




}
