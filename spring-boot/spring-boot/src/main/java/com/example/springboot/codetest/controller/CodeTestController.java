package com.example.springboot.codetest.controller;

import com.example.springboot.codetest.pojo.User;
import com.example.springboot.codetest.service.CodeTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CodeTestController {

    @Autowired
    CodeTestService codeTestService;

    @GetMapping("/test")
    public String index() {
        return "Greetings from Mike!";
    }

    @GetMapping("/userProjects")
    @ResponseBody
    public List<User> getUserProjects() {
        return codeTestService.getUserProjects();
    }

}