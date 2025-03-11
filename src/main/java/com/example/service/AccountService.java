package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    public AccountRepository accountRepo;

    public ResponseEntity<Account> addAccount(Account account) {
        if (account.getPassword().length() < 4) {
            return ResponseEntity.status(400).body(null); }
        if (account.getUsername().isEmpty()) {
            return ResponseEntity.status(400).body(null); }
        if (accountRepo.existsByUsername(account.getUsername())) {
            return ResponseEntity.status(409).body(null); }

        return ResponseEntity.status(200).body(accountRepo.save(account));
    }
    
    public ResponseEntity<Account> loginAccount(Account account) {
        if (accountRepo.findByUsernameAndPassword(account.getUsername(),account.getPassword()) == null) {
            return ResponseEntity.status(401).body(null); }
                
        return ResponseEntity.status(200).body(accountRepo.findByUsernameAndPassword(account.getUsername(),account.getPassword()));
    }

}
