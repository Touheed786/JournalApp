package org.touheed.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.touheed.journalApp.entity.JournalEntry;
import org.touheed.journalApp.service.JournalService;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private final JournalService journalService;
    JournalController(JournalService journalService){
        this.journalService = journalService;
    }

    @PostMapping("/save/{userId}")
    public ResponseEntity<Object> saveData(@RequestBody JournalEntry journalEntry,@PathVariable long userId){
        try {
             return ResponseEntity.ok(journalService.save(journalEntry,userId));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll/{pageNumber}/{size}")
    public ResponseEntity<Object> getAllWithPagination(@PathVariable("pageNumber") int pageNumber,@PathVariable("size") int size){
        return ResponseEntity.ok(journalService.findAllWithPagination(pageNumber,size));
    }

    @GetMapping("/getAllByLoggedUser")
    public ResponseEntity<Object> getAllJournals(){
        return ResponseEntity.ok(journalService.findAllWithUser());
    }

    @GetMapping("/getJournalByUser/{userName}")
    public ResponseEntity<Object> getjournal(@PathVariable("userName") String userName){
        return journalService.findJournalsByUser(userName);
    }


}
