package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.Group;
import com.juggernauts.todoapp.repos.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService {
    private GroupRepo groupRepo;

    @Autowired
    public void setGroupRepo(GroupRepo groupRepo) {this.groupRepo = groupRepo;}

    @Transactional
    public int addGroup(Group group) {
        if (group.getName() != null && !groupRepo.existsById(group.getGroupId())) {
            groupRepo.save(group);
            return 1;
        }
        return -1;
    }

    @Transactional
    public int deleteGroup(int id) {
        boolean groupExists = groupRepo.existsById(id);
        if (groupExists) {
            groupRepo.deleteById(id);
            return 1;
        }
        return -1;
    }

    @Transactional
    public int updateGroup(int id, String name) {
        Group group = groupRepo.findById(id).orElseThrow(() ->
                new IllegalStateException("Group with this id not found"));

        if(name != null && name.length() > 0 && !name.equals(group.getName())){
            group.setName(name);
            return 1;
        }
        return -1;
    }

    public int viewGroup(int id){
        boolean memberExists = groupRepo.existsById(id);
        if(memberExists){
            groupRepo.findById(id);
            return 1;

        } return -1;
    }

    public List<Group> viewAllGroups() {
        List<Group> groupList = groupRepo.findAll();
        if(groupList.isEmpty()){
            return null;
        }
        return groupList;
    }

}
