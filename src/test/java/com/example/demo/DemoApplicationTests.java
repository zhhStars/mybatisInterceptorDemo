package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    void contextLoads() {
        List<Role> roleList = new ArrayList<>();
        addRole(roleList);
        testService.batchInsert(roleList);
    }

    private void addRole(List<Role> roleList){
        for (int i = 0; i < 5; i++) {
            Role role = new Role();
            role.setId(UUID.randomUUID().toString());
            role.setDescription("这是描述" + i);
            role.setIsDeleted("0");
            role.setRoleName("test角色" + i);
            roleList.add(role);
        }
    }

    @Test
    void test1(){
        List<Role> list = new ArrayList<>();
        Role role = new Role();
        role.setIsDeleted("0");
        list.add(role);
        list.forEach(a-> System.out.println(a));

        role.setRoleName("那是");
        list.forEach(a-> System.out.println(a));
    }

}
