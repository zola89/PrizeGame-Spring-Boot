package com.lazar.prizegame.service;

import java.util.List;

import com.lazar.prizegame.model.Code;
import com.lazar.prizegame.model.User;

public interface CodeService {
    Iterable<Code> findAll();


    Code findOne(int id);
    
    Iterable<Code> findByUserId(int userId);
    
    Code findByPrizeCode(String prizeCode, int userId);
    
    void save(Code code);

    void delete(int id);
}