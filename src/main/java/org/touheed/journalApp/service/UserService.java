package org.touheed.journalApp.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.touheed.journalApp.JournalApplication;
import org.touheed.journalApp.entity.JournalEntry;
import org.touheed.journalApp.entity.User;
import org.touheed.journalApp.repo.JournalRepo;
import org.touheed.journalApp.repo.UserRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    PasswordEncoder passwordEncoder;


    public User saveUser(User user){
        user.setRoles(Arrays.asList("USER"));
        User exists = userRepo.findByUserName(user.getUserName());
        if (exists != null){
            throw new RuntimeException("User is Already Exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public ResponseEntity<Object> saveAdminUser(User user){
        user.setRoles(Arrays.asList("ADMIN","USER"));
        User exists = userRepo.findByUserName(user.getUserName());
        if (exists != null){
            log.info("User is Already Exists");
            log.error("User is Already Exists");
            log.warn("User is Already Exists");
            log.trace("User is Already Exists");
            log.debug("User is Already Exists");

            return ResponseEntity.badRequest().body("User is Already Exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepo.save(user));
    }

    public User updateUser(User user){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User exists = userRepo.findByUserName(userName);
        exists.setUserName(user.getUserName());
        exists.setPassword(user.getPassword());
//        exists.setRoles(Arrays.asList("ADMIN","USER"));
        return saveUser(exists);
    }

    public User findByUser(String userName){
        User user = userRepo.findByUserName(userName);
        return user;
    }
////    @Transactional
//    public User deleteUser(String userName){
//        User user = userRepo.findByUserName(userName);
//
//        removeALlJournal(user.getJournalEntries());
////        throw new RuntimeException("Unable to delete user");
////        userRepo.delete(user);
//        return user;
//    }
//
//    public void removeALlJournal(List<JournalEntry> journalEntries){
////        journalRepo.deleteAll(journalEntries);
//        journalEntries.forEach(data->{
//            journalRepo.delete(data);
//        });
//
//    }


    public ResponseEntity<Object> findAllUser(){

        List<User> users = userRepo.findAll();

        if (users == null || users.isEmpty()){
            return ResponseEntity.badRequest().body("Not Users Present");
        }
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<Object> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUserName(authentication.getName());
        userRepo.delete(user);
        return ResponseEntity.ok("User deleted..");

//        removeALlJournal(user);
//        throw new RuntimeException("Unable to delete user"); // forcefully thrown
    }

    public void removeALlJournal(User user){
        journalRepo.deleteByUserId(user.getId());
    }


}
