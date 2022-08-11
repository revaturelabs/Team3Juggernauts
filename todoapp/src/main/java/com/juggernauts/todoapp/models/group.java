package com.juggernauts.todoapp.models;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    private String name;

    public group() {
    }

    public group(int groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }
}
