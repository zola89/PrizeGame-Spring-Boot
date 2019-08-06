package com.lazar.prizegame.service;

import com.lazar.prizegame.dto.CodeDTO;
import com.lazar.prizegame.model.Code;
import com.lazar.prizegame.model.enums.PrizeType;
import com.lazar.prizegame.repository.CodeRepository;
import com.lazar.prizegame.utils.reflection.UtilsReflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeRepository codeRepository;
    
    @Autowired
    private UserService userService;

    @Override
    public List<Code> findAll() {
        List<Code> list = new ArrayList<Code>();
        codeRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public List<Code> findByUserId(int userId) {
        List<Code> list = new ArrayList<>();
        codeRepository.findByUserId(userId).forEach(list::add);
        return list;
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
    public Code saveDTO(CodeDTO dto) {
        Code t = createNewInstanceOfEntityClass();
        t = mapEntityFromDTO(dto, t);
        return codeRepository.save(t);
    }

    @Override
    public Code update(Code t) {
        return codeRepository.save(t);
    }

    @Override
    public void delete(int id) {
        codeRepository.delete(id);
    }

    @Override
    public Code updateDTO(CodeDTO dto) {

        Code code = codeRepository.findOne(dto.getId());

        if (code == null) {
            throw new RuntimeException(getEntityClass() + " entity with id: " + dto.getId() + ", does not exists.");
        }

        Code t = mapEntityFromDTO(dto, code);

        return codeRepository.save(t);
    }

    @Override
    public CodeDTO mapDTOFromEntity(Code code, CodeDTO codeDTO) {
        if (code == null) {
            return null;
        }

        codeDTO.setId(code.getId());
        codeDTO.setPrizeCode(code.getPrizeCode());
        codeDTO.setPrizeType(code.getPrizeType().getValue());
        codeDTO.setPrizeTime(code.getPrizeTime());
        codeDTO.setUserId(code.getUser().getId());

        return codeDTO;
    }

    @Override
    public CodeDTO findDtoById(int id) {
        return mapDTOFromEntity(findOne(id), new CodeDTO());
    }

    @Override
    public Code mapEntityFromDTO(CodeDTO dto, Code code) {

        code.setId(dto.getId());

        code.setPrizeCode(dto.getPrizeCode());
        code.setPrizeType(dto.getPrizeType() != null ? PrizeType.valueOf(dto.getPrizeType()) : PrizeType.SHIRT);
        code.setPrizeTime(dto.getPrizeTime());
        code.setUser(userService.findOne(dto.getUserId()));

        return code;
    }

    protected Code createNewInstanceOfEntityClass() {

        Code code = null;

        try {
            code = getEntityClass().newInstance();
        } catch (Exception e) {
        }

        return code;
    }

    protected Class<Code> getEntityClass() {
        return UtilsReflection.getParameterClazzOfType(this.getClass().getGenericSuperclass(), 0);
    }

    protected Class<CodeDTO> getEntityDTOClass() {
        return UtilsReflection.getParameterClazzOfType(this.getClass().getGenericSuperclass(), 1);
    }

}