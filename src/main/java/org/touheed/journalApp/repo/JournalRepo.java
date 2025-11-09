package org.touheed.journalApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.touheed.journalApp.entity.JournalEntry;
import org.touheed.journalApp.entity.User;

import java.util.List;

@Repository
public interface JournalRepo extends JpaRepository<JournalEntry,Long> {
    @Query(value = "delete from journal where  user_id = :userId",nativeQuery = true)
    public void deleteByUserId(@Param("userId") long userId);

    public List<JournalEntry> findByUser(@Param("user") User user);
}
    