package com.balzzak.accountservice.repo;

import com.balzzak.accountservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
