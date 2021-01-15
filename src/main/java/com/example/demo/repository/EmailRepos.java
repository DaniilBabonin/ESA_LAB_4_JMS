package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Email;

public interface EmailRepos extends CrudRepository<Email, Long> {
}
