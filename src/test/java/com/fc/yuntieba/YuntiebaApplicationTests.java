package com.fc.yuntieba;

import com.fc.yuntieba.entity.User;
import com.fc.yuntieba.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YuntiebaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    public IUserService userService;
    @Test
    public void myTest(){
        User user = userService.getById(1);
        System.out.println(user);
    }

}
