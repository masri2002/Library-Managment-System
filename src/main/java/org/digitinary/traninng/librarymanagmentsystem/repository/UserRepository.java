package org.digitinary.traninng.librarymanagmentsystem.repository;

import org.digitinary.traninng.librarymanagmentsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
