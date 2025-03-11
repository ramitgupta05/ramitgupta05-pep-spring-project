package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepo;

    public ResponseEntity<Message> create(Message message) {
        if (message.getMessageText().isBlank()) { 
            return ResponseEntity.status(400).body(null); }
        if (message.getMessageText().length() > 255) {
            return ResponseEntity.status(400).body(null); }
        if (messageRepo.findByPostedBy(message.getPostedBy()).isEmpty()) {
            return ResponseEntity.status(400).body(null); }
        
            messageRepo.save(message);
        return ResponseEntity.status(200).body(message);
    }

    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(200).body(messageRepo.findAll());
    }

    public ResponseEntity<Message> getMessageById(Integer messageId) {
        Optional<Message> optional = messageRepo.findById(messageId);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());   }
        else {return ResponseEntity.status(200).build();}
        /*if (!messageRepo.findById(messageId).isPresent()) {  
            return ResponseEntity.status(200).body(new Message()); } 
        
        return ResponseEntity.status(200).body(messageRepo.getById(messageId));*/
    }

    public ResponseEntity<Object> deleteMessageById(Integer messageId) {
        if (messageRepo.existsById(messageId)) {
            messageRepo.deleteById(messageId); 
            return ResponseEntity.status(200).body("1");} 
        
        return ResponseEntity.status(200).body("");
    }
        
    public ResponseEntity<String> updateMessageById(String messageText, Integer messageId) {
        if (messageText.length() > 255 || messageText.isEmpty()) {
            return ResponseEntity.status(400).body(null); }
        Optional<Message> optional = messageRepo.findById(messageId);
        if (!optional.isPresent()) {
            return ResponseEntity.status(400).body(null); }
        
        Message message = optional.get();
        message.setMessageText(messageText);
        messageRepo.save(message);

        return ResponseEntity.status(200).body("1"); 
    }
        
    public ResponseEntity<List<Message>> getAllMessagesForUser(Integer accountId) {
        return ResponseEntity.status(200).body(messageRepo.findByPostedBy(accountId));
    }
}

