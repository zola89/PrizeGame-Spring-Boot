package com.lazar.prizegame.controller;

import com.lazar.prizegame.dto.CodeDTO;
import com.lazar.prizegame.model.Code;
import com.lazar.prizegame.model.User;
import com.lazar.prizegame.service.CodeService;
import com.lazar.prizegame.service.UserService;
import com.lazar.prizegame.utils.headers.Headers;
import com.lazar.prizegame.utils.headers.HeadersHelper;
import com.lazar.prizegame.utils.messages.Messages;
import com.lazar.prizegame.utils.messages.MessagesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/codes")
public class CodeController {

    @Autowired
    private CodeService codeService;
    
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<Code> getAll() {
        return codeService.findAll();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CodeDTO  codeDTO) {

        Code code = codeService.saveDTO(codeDTO);

        HttpHeaders headers = HeadersHelper.add(Headers.SUCCESS_MESSAGE, MessagesHelper.generate(Messages.CREATE_SUCCESS, Code.class.getSimpleName(), code.getPrizeCode()));

        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public Code getById(@PathVariable(value = "id") int id) {

        return codeService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") int id) {

        userService.delete(id);

        HttpHeaders headers = HeadersHelper.add(Headers.SUCCESS_MESSAGE, MessagesHelper.generate(Messages.DELETE_SUCCESS, Code.class.getSimpleName()));

        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateById(@PathVariable(value = "id") Long id, @RequestBody CodeDTO codeDTO) {

        Code code = codeService.updateDTO(codeDTO);

        HttpHeaders headers = HeadersHelper.add(Headers.SUCCESS_MESSAGE, MessagesHelper.generate(Messages.UPDATE_SUCCESS, Code.class.getSimpleName(), code.getPrizeCode()));

        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/prize/{prizeCode}")
    public Code getByPrizeCode(@PathVariable(value = "prizeCode") String prizeCode) {
        return codeService.getByPrizeCode(prizeCode);
    }
    
    @GetMapping("/user/{user_id}")
    public List<Code> getCodeByUserId(@PathVariable int user_id) {
        return codeService.findByUserId(user_id);
    }
    
    @PostMapping(value = "/prize")
    public CodeDTO insertUserPrizeCode(@RequestParam( required = true ) String prize_code, @RequestParam( required = true ) int user_id) {
    	return codeService.insertUserPrizeCode(prize_code, user_id);
    }

}
