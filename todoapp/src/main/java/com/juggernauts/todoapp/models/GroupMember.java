//package com.juggernauts.todoapp.models;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "group_members")
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class GroupMember {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="group_member_id")
//    private int groupMemberId;
//
//    @Column(name = "is_admin",columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private boolean isAdmin;
//
//    @Column(name = "user_id",nullable = false, columnDefinition = "INTEGER")
//    private int userId;
//
//    @Column(name = "group_id", nullable = false, columnDefinition = "INTEGER")
//    private int groupId;
//}