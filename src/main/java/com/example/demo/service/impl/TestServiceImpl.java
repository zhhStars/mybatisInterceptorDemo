package com.example.demo.service.impl;

import com.example.demo.config.Page;
import com.example.demo.entity.Role;
import com.example.demo.repository.TestMapper;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public Page getList(Page page) {
        page.getParam().put("data", testMapper.getRoleList(page));
        return page;
    }
}
