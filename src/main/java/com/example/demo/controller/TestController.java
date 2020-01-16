package com.example.demo.controller;

import com.example.demo.config.Page;
import com.example.demo.entity.Role;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public List<Role> getList(@RequestBody Page page){
        List<Role> list = testService.getList(page);
        return list;
    }
}
