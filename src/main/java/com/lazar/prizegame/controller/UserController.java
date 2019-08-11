package com.lazar.prizegame.controller;

import com.lazar.prizegame.dto.UserDTO;
import com.lazar.prizegame.dto.enums.UserRoleDTO;
import com.lazar.prizegame.model.User;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UserDTO userDTO) {

        User user = userService.saveDTO(userDTO);

        HttpHeaders headers = HeadersHelper.add(Headers.SUCCESS_MESSAGE, MessagesHelper.generate(Messages.CREATE_SUCCESS, User.class.getSimpleName(), user.getName()));

        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public User getById(@PathVariable(value = "id") int id) {
        return userService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") int id) {

        userService.delete(id);

        HttpHeaders headers = HeadersHelper.add(Headers.SUCCESS_MESSAGE, MessagesHelper.generate(Messages.DELETE_SUCCESS, User.class.getSimpleName()));

        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateById(@PathVariable(value = "id") Long id, @RequestBody UserDTO userDTO) {

        User user = userService.updateDTO(userDTO);

        HttpHeaders headers = HeadersHelper.add(Headers.SUCCESS_MESSAGE, MessagesHelper.generate(Messages.UPDATE_SUCCESS, User.class.getSimpleName(), user.getName()));

        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/user_roles")
    public List<UserRoleDTO> getUserRoles() {
        return userService.findUserRoles();
    }


}