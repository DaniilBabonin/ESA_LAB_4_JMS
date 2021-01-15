package com.example.demo.JMS;

import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsSenderService {

    private final JmsTemplate jmsTemplate;


    @Autowired
    public JmsSenderService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    public void sendCatUpdate(Cat cat, EventType eventType){
        Email email = new Email();
        email.setReceiver("daniilbabonin17@gmail.com");
        email.setSubject("Cat [" + eventType.name() + ']');
        String bodyPattern = "Здравствуйте, Даня, !\n\n" +
                "Произошли изменения!\n" +
                "Тип изменения: %s\n\n" +
                "Котик: %s";
        String body = String.format(bodyPattern, eventType.name(), cat.toString());
        email.setBody(body);
        jmsTemplate.convertAndSend("mailbox", email);
    }

    public <T> void sendEvent(Class<T> entityClass, T entity, EventType eventType){
        Event event = new Event();
        event.setEventType(eventType);
        event.setEntity(entity.toString());
        event.setEntityClass(entityClass.getSimpleName());
        jmsTemplate.convertAndSend("eventbox", event);
    }


}
