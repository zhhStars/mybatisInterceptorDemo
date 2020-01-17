package com.example.demo.repository;

import com.example.demo.config.Page;
import com.example.demo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMapper {
    List<Role> getRoleList(Page page);

    int batchInsert(List<Role> list);
}
