package com.example.demo.controller;

import com.example.demo.config.Page;
import com.example.demo.entity.Role;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private TestService testService;

    private static HashMap<String, Object> result = new HashMap<String, Object>();

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Object getList(@RequestBody Page page){
        try {
            List<Role> list = testService.getList(page);
            result.put("code", 200);
            result.put("message", "查询成功");
            result.put("data", list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
