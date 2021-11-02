package com.example.springboot.codetest.service;

import com.example.springboot.codetest.pojo.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    HttpService httpService;

    public List<User> getRegisteredUsers() {
        System.out.println("Called getRegisteredUsers...");
        try {
            String result = httpService.sendGetRequest("https://5c3ce12c29429300143fe570.mockapi.io/api/registeredusers");
            List<User> registerUsers = new ObjectMapper().readValue(result, new TypeReference<List<User>>(){});
            return registerUsers;
        } catch (IOException e) {
            System.out.println("Exception getting registered users: "+e);
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getUnregisteredUsers() {
        System.out.println("Called getUnregisteredUsers...");
        try {
            String result = httpService.sendGetRequest("https://5c3ce12c29429300143fe570.mockapi.io/api/unregisteredusers");
            List<User> registerUsers = new ObjectMapper().readValue(result, new TypeReference<List<User>>(){});
            return registerUsers;
        } catch (IOException e) {
            System.out.println("Exception getting registered users: "+e);
            e.printStackTrace();
            return null;
        }
    }

}
