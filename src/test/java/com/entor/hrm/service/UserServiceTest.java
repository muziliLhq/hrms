package com.entor.hrm.service;

import com.entor.hrm.mapper.UserMapper;
import com.entor.hrm.po.User;
import com.entor.hrm.service.impl.PageModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 用户服务测试类
 */
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetAll() {
        System.out.println(userService.getAll());
    }

    @Test
    public void testGetByPage() {
        User user = new User();
        user.setUsername("小");
        user.setStatus(1);
        PageModel<User> pageModel = userService.getByPage(user, 1, 2);
        System.out.println(pageModel.getFirstLimitParam());
        System.out.println(pageModel.getPageSize());
        System.out.println(pageModel.getRecordCount());
        System.out.println(pageModel.getPageList());
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setStatus(1);
        user.setUsername("muzili");
        user.setPassword("123");
        user.setLoginName("Joke");
        userService.save(user);
    }

    @Test
    public void testDelteById() {
        userService.removeById(3);
    }

    @Test
    public  void testUpdateUser() {
        User user = new User();
        user.setPassword("123");
        user.setUsername("goodBoy");
        user.setStatus(0);
        user.setId(5);
        userService.modifyUser(user);
    }

    @Test
    public void testBatchDeleteUser() {
        userService.batchRemoveUser(new Integer[]{9,10});
    }
}
