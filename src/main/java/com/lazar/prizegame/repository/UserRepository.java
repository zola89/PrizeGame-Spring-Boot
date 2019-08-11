package com.lazar.prizegame.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lazar.prizegame.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByNameContaining(String q);
    
    User findByEmail(String email);

}