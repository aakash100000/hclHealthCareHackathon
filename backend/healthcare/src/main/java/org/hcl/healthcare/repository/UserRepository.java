package org.hcl.healthcare.repository;

import org.hcl.healthcare.entity.User;
import org.hcl.healthcare.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    List<User> findAllByType(UserType type);
   // List<User> findByUserType(UserType userType);
}