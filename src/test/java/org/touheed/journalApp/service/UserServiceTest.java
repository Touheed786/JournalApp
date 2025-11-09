package org.touheed.journalApp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.touheed.journalApp.entity.User;
import org.touheed.journalApp.repo.UserRepo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;


    @Test
    public void testFindByUserName(){
        User user = userService.findByUser("touheed");
        assertTrue(!user.getRoles().isEmpty());
    }


    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,4,6",
            "4,6,10"
    })
    void testDivide(int a,int b, int expected) {
        assertEquals(expected,a+b);
    }
}
