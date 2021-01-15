package com.example.demo.JMS;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    private final EmailSender emailSender;
    private final EventRepos eventRepo;
    private final EmailRepos emailRepo;

    @Autowired
    public Listener(EmailSender emailSender, EventRepos eventRepo, EmailRepos emailRepo) {
        this.emailSender = emailSender;
        this.eventRepo = eventRepo;
        this.emailRepo = emailRepo;
    }

    @JmsListener(destination = "eventbox", containerFactory = "myFactory")
    public void receiveEvent(Event event) {
        eventRepo.save(event);
    }

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        emailSender.send(email);
        emailRepo.save(email);
    }
}
