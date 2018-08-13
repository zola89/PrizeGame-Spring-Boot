package com.lazar.prizegame.service;

import java.util.List;

import com.lazar.prizegame.model.Code;

public interface CodeService {
    Iterable<Code> findAll();


    Code findOne(int id);
    
    Code findByPrizeCode(String prizeCode);
    
    void save(Code code);

    void delete(int id);
}