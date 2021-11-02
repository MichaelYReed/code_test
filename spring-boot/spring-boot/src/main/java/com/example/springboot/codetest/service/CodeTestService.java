package com.example.springboot.codetest.service;

import com.example.springboot.codetest.pojo.ProjectMembership;
import com.example.springboot.codetest.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeTestService {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    public List<User> getUserProjects() {
        System.out.println("Step 1");

        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(userService.getRegisteredUsers());
        allUsers.addAll(userService.getUnregisteredUsers());

        List<ProjectMembership> projectMemberships = projectService.getProjectMemberships();
        Map<String, List<String>> userToProjectsMap = buildUserToProjectsMap(projectMemberships);
        for (User u : allUsers) {
            if (userToProjectsMap.containsKey(u.getId())){
                List<String> existingProjectMemberships = userToProjectsMap.get(u.getId());
                System.out.println("User id "+u.getId()+" has a membership in these projects: "+existingProjectMemberships);
                u.setProjectIds(existingProjectMemberships.toArray(new String[0]));
            }
        }
        return allUsers;
    }

    private Map<String, List<String>> buildUserToProjectsMap(List<ProjectMembership> projectMemberships) {
        Map<String, List<String>> userToProjectsMap = new HashMap<>();
        for (ProjectMembership pm : projectMemberships) {
            userToProjectsMap.putIfAbsent(pm.getUserId(), new ArrayList<>());
            List<String> existingProjectMemberships = userToProjectsMap.get(pm.getUserId());
            existingProjectMemberships.add(pm.getProjectId());
            userToProjectsMap.put(pm.getUserId(), existingProjectMemberships);
        }
        return userToProjectsMap;
    }
}
