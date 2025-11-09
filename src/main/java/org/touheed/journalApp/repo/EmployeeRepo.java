package org.touheed.journalApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.touheed.journalApp.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
}
