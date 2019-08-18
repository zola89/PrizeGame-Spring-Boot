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
@RequestMapping("/users")
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

    @PostMapping(value = "/validate")
    public ResponseEntity<UserDTO> validateUser(@RequestBody UserDTO userDTO) {
    	
    	if(userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
    		HttpHeaders headers = HeadersHelper.add(Headers.ERROR_MESSAGE, "Email can not be empty.");
    		return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    	}
    	if(userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
    		HttpHeaders headers = HeadersHelper.add(Headers.ERROR_MESSAGE, "Password can not be empty.");
    		return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    	}
    	
    	User validatedUser = userService.validateUser(userDTO.getEmail(), userDTO.getPassword());
    	
    	if(validatedUser == null) {
    		
    		HttpHeaders headers = HeadersHelper.add(Headers.ERROR_MESSAGE, "User not found.");
    		return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
    	}
    	
    	UserDTO authenticatedUserDTO = new UserDTO();
    	authenticatedUserDTO = userService.mapDTOFromEntity(validatedUser, authenticatedUserDTO);
    	
    	HttpHeaders headers = HeadersHelper.add(Headers.SUCCESS_MESSAGE, "User login success.");

        return new ResponseEntity<>(authenticatedUserDTO, headers, HttpStatus.OK);
    }
}