package com.juggernauts.todoapp.services;

import com.juggernauts.todoapp.models.GroupMember;
import com.juggernauts.todoapp.repos.GroupMemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMemberService {
    private GroupMemberRepo groupMemberRepo;

    @Autowired
    public void setGroupMemberRepo(GroupMemberRepo groupMemberRepo) {
        this.groupMemberRepo = groupMemberRepo;
    }

    public int addGroupMember(GroupMember groupMember) {
        boolean memberExists = groupMemberRepo.existsById(groupMember.getGroupMemberId());
        if (!memberExists) {
            System.out.println("this user doesn't exist");
            return -1;
        }
        groupMemberRepo.save(groupMember);
        return 1;
    }

    public int deleteGroupMember(GroupMember groupMember) {
        boolean memberExists = groupMemberRepo.existsById(groupMember.getGroupMemberId());
        if (!memberExists) {
            System.out.println("this user doesn't exist");
        }
        groupMemberRepo.deleteById(groupMember.getGroupMemberId());
        return 1;
    }

    public int updateGroupMember(GroupMember groupMember) {
        if (groupMemberRepo.existsById(groupMember.getGroupMemberId())) {
            groupMember.setAdmin(!groupMember.isAdmin());
            return 1;
        }
        return -1;
    }

    @Transactional
    public int saveAllToGroup(List<GroupMember> groupMember) {
        List<GroupMember> listOfMembers = (List<GroupMember>) groupMemberRepo.saveAll(groupMember);
        return 1;
    }

    public int viewGroupMember(int id) {
        boolean memberExists = groupMemberRepo.existsById(id);
        if (!memberExists) {
            return -1;
        }
        groupMemberRepo.findById(id);
        return 1;
    }

    public List<GroupMember> viewAllGroupMembers(int id) {
        List<GroupMember> listOfMembers = groupMemberRepo.findAll();
    if(listOfMembers.isEmpty()){
        return null;
    }
    return listOfMembers;
}

public List<GroupMember> viewAllAdmins( GroupMember groupMembers){
        List<GroupMember> allMembers = groupMemberRepo.findAll();
       List<GroupMember> areAdmins = allMembers.stream().filter(GroupMember -> groupMembers.isAdmin())
                .collect(Collectors.toList());

        if(areAdmins.isEmpty()){
        return null;
        }
        return areAdmins;
}

}
