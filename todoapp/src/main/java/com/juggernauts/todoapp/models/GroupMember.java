package com.juggernauts.todoapp.models;

import lombok.*;

import javax.persistence.*;

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
//    // NON-RELATIONAL FIELDS
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="group_member_id")
//    private int groupMemberId;
//
//    @Column(name = "is_admin",columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private boolean isAdmin;
//
//
//    // RELATIONAL FIELDS
//
//    // used in previous version
////    @Column(name = "user_id",nullable = false, columnDefinition = "INTEGER")
////    private int userId;
////
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", columnDefinition = "INTEGER")
//    private User user;
//
//    // used in previous version
////    @Column(name = "group_id", nullable = false, columnDefinition = "INTEGER")
////    private int groupId;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
//    private Group group;
//}