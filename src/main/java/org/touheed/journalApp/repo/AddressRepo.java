package org.touheed.journalApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.touheed.journalApp.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
}
