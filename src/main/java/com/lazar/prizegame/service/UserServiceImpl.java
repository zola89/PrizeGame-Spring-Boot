package com.lazar.prizegame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazar.prizegame.model.User;
import com.lazar.prizegame.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> search(String q) {
        return userRepository.findByNameContaining(q);
    }

    @Override
    public User findOne(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public void save(User contact) {
        userRepository.save(contact);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }
}