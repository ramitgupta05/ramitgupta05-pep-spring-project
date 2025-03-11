package com.example.repository;

import org.springframework.stereotype.Repository;
import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    
        boolean existsByUsername(String username);
        @SuppressWarnings("null")
        boolean existsById(Integer accountId);

        Account findByUsername(String username);
        @SuppressWarnings("null")
        Account getById(Integer accountId);
        Account findByUsernameAndPassword(String username, String password);


}
