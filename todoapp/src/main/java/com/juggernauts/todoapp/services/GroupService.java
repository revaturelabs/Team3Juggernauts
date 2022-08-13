package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Category;
import com.juggernauts.todoapp.models.Group;
import com.juggernauts.todoapp.models.GroupMember;
import com.juggernauts.todoapp.repos.GroupMemberRepo;
import com.juggernauts.todoapp.repos.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private GroupRepo groupRepo;

    @Autowired
    public void setGroupRepo(GroupRepo groupRepo) {this.groupRepo = groupRepo;}

    public int addGroup(Group group) {
        if (group.getName() != null && !groupRepo.existsById(group.getGroupId())) {
            groupRepo.save(group);
            return 1;
        }
        return -1;
    }

    public int deleteGroup(int id) {
        if (id > 0) {
            groupRepo.deleteById(id);

            return 1;
        }
        return -1;
    }

    public int updateGroup(int id, String name) {
        Group group = groupRepo.findById(id).orElseThrow(() ->
                new IllegalStateException("Group with this id not found"));

        if(name != null && name.length() > 0 && !name.equals(group.getName())){
            group.setName(name);
            return 1;
        }
        return -1;
    }

}
