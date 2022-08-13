package com.juggernauts.todoapp.web;

import com.juggernauts.todoapp.models.Group;
import com.juggernauts.todoapp.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> createNewGroup(@RequestBody Group group) {
        groupService.addGroup(group);
        return ResponseEntity.ok(group);
    };
}
