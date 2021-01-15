package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Event;

public interface EventRepos extends CrudRepository<Event, Long> {
}
