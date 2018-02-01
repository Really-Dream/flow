package com.dream.activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Dream
 * 2018/2/1.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdentityServiceTest {

    @Autowired
    IdentityService identityService;

    @Test
    public void createUser(){
        User user = identityService.newUser("lisi");
        user.setFirstName("四");
        user.setLastName("李");
        user.setEmail("lisi@163.com");
        user.setPassword("lisi");
        identityService.saveUser(user);
    }

    @Test
    public void createGroup(){
        Group group = identityService.newGroup("develop");
        group.setName("开发人员");
        group.setType("develop");
        identityService.saveGroup(group);
    }

    @Test
    public void createMembership(){
        identityService.createMembership("lisi","develop");
    }

    @Test
    public void queryMemberOfGroup(){
        List<User> user = identityService.createUserQuery().memberOfGroup("admin").list();
        System.out.println(user);
    }

    @Test
    public void queryGroupOfMember(){
        List<Group> groups = identityService.createGroupQuery().groupMember("lisi").list();
        System.out.println(groups);
    }


}
