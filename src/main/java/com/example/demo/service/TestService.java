package com.example.demo.service;

import com.example.demo.config.Page;
import com.example.demo.entity.Role;

import java.util.List;

public interface TestService {
    List<Role> getList(Page page);
}
