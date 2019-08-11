package com.lazar.prizegame.service;

import java.util.List;

import com.lazar.prizegame.dto.UserDTO;
import com.lazar.prizegame.dto.enums.UserRoleDTO;
import com.lazar.prizegame.model.User;

public interface UserService {

    List<User> findAll();

    List<User> search(String q);

    User findOne(int id);

    void save(User contact);

    User saveDTO(UserDTO dto);

    User update(User t);

    void delete(int id);

    List<UserRoleDTO> findUserRoles();

    UserDTO mapDTOFromEntity(User user, UserDTO userDTO);

    User mapEntityFromDTO(UserDTO dto, User user);

    UserDTO findDtoById(int id);

    User updateDTO(UserDTO dto);
    
    User validateUser(String email, String password);
}