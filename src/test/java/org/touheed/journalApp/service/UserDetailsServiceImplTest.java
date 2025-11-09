package org.touheed.journalApp.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.touheed.journalApp.entity.User;
import org.touheed.journalApp.repo.UserRepo;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loasUserByUserName(){

        User user = new User();
        user.setUserName("touheed");
        user.setPassword("touheed");
        user.setRoles(new ArrayList<>(List.of("USER")));

        when(userRepo.findByUserName(anyString())).thenReturn(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(anyString());

        assertEquals("touheed", userDetails.getUsername());

    }
}
