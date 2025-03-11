package com.example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    
    @Autowired
    private AccountService accountservice;
    @Autowired
    private MessageService messageservice;
    
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        return accountservice.addAccount(account);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        return accountservice.loginAccount(account);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return messageservice.create(message);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return messageservice.getAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        return messageservice.getMessageById(messageId);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Object> deleteMessageById(@PathVariable Integer messageId) {
        return messageservice.deleteMessageById(messageId);
    }
    
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<String> updateMessageById(@RequestBody HashMap<String, String> body, @PathVariable Integer messageId) {
        return messageservice.updateMessageById(body.get("messageText"),messageId);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesForUser(@PathVariable Integer accountId) {
        return messageservice.getAllMessagesForUser(accountId);
    }
}
