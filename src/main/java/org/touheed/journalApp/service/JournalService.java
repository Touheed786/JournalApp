package org.touheed.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.touheed.journalApp.entity.JournalEntry;
import org.touheed.journalApp.entity.User;
import org.touheed.journalApp.repo.JournalRepo;
import org.touheed.journalApp.repo.UserRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UserRepo userRepo;

    public JournalEntry save(JournalEntry journalEntry,long userId){
        journalEntry.setDate(LocalDateTime.now());
        Optional<User> user = userRepo.findById(userId);
        user.ifPresent((oldUser)->{
            journalEntry.setUser(oldUser);
            JournalEntry journalEntry1 =  journalRepo.save(journalEntry);
            oldUser.getJournalEntries().add(journalEntry1);
            userRepo.save(oldUser);
        });
        return journalEntry;
    }

    public List<JournalEntry> findAllWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<JournalEntry> res = journalRepo.findAll(pageable);
        return res.getContent();
    }

    public List<JournalEntry> findAllWithUser() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepo.findByUserName(userName);
        List<JournalEntry> res = user.getJournalEntries();
        return res;
    }

    public ResponseEntity<Object> findJournalsByUser(String userName){
        User user = userRepo.findByUserName(userName);
        if (user != null){

            List<JournalEntry> users = journalRepo.findByUser(user);
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.badRequest().body("User Not Found");

    }
}
