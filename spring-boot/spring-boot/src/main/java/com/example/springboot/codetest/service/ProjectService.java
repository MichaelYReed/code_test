package com.example.springboot.codetest.service;

import com.example.springboot.codetest.pojo.ProjectMembership;
import com.example.springboot.codetest.pojo.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    HttpService httpService;

    public List<ProjectMembership> getProjectMemberships() {
        System.out.println("Called getProjectMemberships...");
        try {
            String result = httpService.sendGetRequest(
                    "https://5c3ce12c29429300143fe570.mockapi.io/api/projectmemberships");
            List<ProjectMembership> projectMemberships = new ObjectMapper().readValue(result, new TypeReference<List<ProjectMembership>>(){});
            return projectMemberships;
        } catch (IOException e) {
            System.out.println("Exception getting project memberships users: "+e);
            e.printStackTrace();
            return null;
        }
    }
}
