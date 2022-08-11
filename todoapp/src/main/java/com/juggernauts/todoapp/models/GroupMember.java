package com.juggernauts.todoapp.models;

import javax.persistence.*;

@Entity
@Table(name = "group_members")
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupMemberId;
    private boolean isAdmin;
    private int userId;
    private int groupId;

    public GroupMember() {
    }

    public GroupMember(int groupMemberId, boolean isAdmin, int userId, int groupId) {
        this.groupMemberId = groupMemberId;
        this.isAdmin = isAdmin;
        this.userId = userId;
        this.groupId = groupId;
    }

    public int getGroupMemberId() {
        return groupMemberId;
    }

    public void setGroupMemberId(int groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "groupMemberId=" + groupMemberId +
                ", isAdmin=" + isAdmin +
                ", userId=" + userId +
                ", groupId=" + groupId +
                '}';
    }
}
