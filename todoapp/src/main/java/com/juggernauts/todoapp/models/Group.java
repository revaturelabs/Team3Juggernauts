package com.juggernauts.todoapp.models;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "groups")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    @Column(length=256, nullable=false, unique=true)
    private String name;

}
