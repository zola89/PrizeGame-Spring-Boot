package com.lazar.prizegame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazar.prizegame.model.Code;
import com.lazar.prizegame.repository.CodeRepository;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeRepository codeRepository;

    @Override
    public Iterable<Code> findAll() {
        return codeRepository.findAll();
    }


    @Override
    public Code findOne(int id) {
        return codeRepository.findOne(id);
    }

    @Override
    public void save(Code code) {
        codeRepository.save(code);
    }

    @Override
    public void delete(int id) {
        codeRepository.delete(id);
    }


	@Override
	public Code findByPrizeCode(String prizeCode) {
		return codeRepository.findByPrizeCode(prizeCode);
	}
}