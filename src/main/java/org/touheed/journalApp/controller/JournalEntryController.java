package org.touheed.journalApp.controller;

import org.springframework.web.bind.annotation.*;
import org.touheed.journalApp.entity.JournalEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> journalEntryMap = new HashMap<>();

    @GetMapping("/getAll")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntryMap.values());
    }

    @PostMapping("/save")
    public String saveEntry(@RequestBody JournalEntry journalEntry){
        journalEntryMap.put(journalEntry.getId(),journalEntry);
        return "data has been saved";
    }


}
