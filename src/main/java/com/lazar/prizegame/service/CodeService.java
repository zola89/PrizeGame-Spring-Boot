package com.lazar.prizegame.service;

import com.lazar.prizegame.dto.CodeDTO;
import com.lazar.prizegame.model.Code;

import java.util.List;

public interface CodeService {

    List<Code> findAll();

    Code findOne(int id);
    
    List<Code> findByUserId(int userId);

    void save(Code code);

    void delete(int id);

    Code saveDTO(CodeDTO dto);

    Code update(Code code);

    CodeDTO mapDTOFromEntity(Code code, CodeDTO codeDTO);

    Code mapEntityFromDTO(CodeDTO dto, Code code);

    CodeDTO findDtoById(int id);

    Code getByPrizeCode(String prizeCode);

    Code updateDTO(CodeDTO dto);
}