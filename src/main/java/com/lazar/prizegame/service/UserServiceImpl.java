package com.lazar.prizegame.service;

import com.lazar.prizegame.dto.UserDTO;
import com.lazar.prizegame.dto.core.EntityDTO;
import com.lazar.prizegame.dto.enums.UserRoleDTO;
import com.lazar.prizegame.model.User;
import com.lazar.prizegame.model.enums.UserRole;
import com.lazar.prizegame.repository.UserRepository;
import com.lazar.prizegame.utils.reflection.UtilsReflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        userRepository.findAll().forEach(list::add);
        return list;
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
    public User saveDTO(UserDTO dto) {
        User t = createNewInstanceOfEntityClass();
        t = mapEntityFromDTO(dto, t);
        return userRepository.save(t);
    }

    @Override
    public User update(User t) {
        return userRepository.save(t);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }


    @Override
    public User updateDTO(UserDTO dto) {

        User user = userRepository.findOne(dto.getId());

        if (user == null) {
            throw new RuntimeException(getEntityClass() + " entity with id: " + dto.getId() + ", does not exists.");
        }

        User t = mapEntityFromDTO(dto, user);

        return userRepository.save(t);
    }




    @Override
    public List<UserRoleDTO> findUserRoles() {

        List<UserRoleDTO> result = new ArrayList<>();

        for (UserRole userRole : UserRole.values()) {
            result.add(new UserRoleDTO(userRole.getValue()));
        }

        return result;
    }

    @Override
    public UserDTO mapDTOFromEntity(User user, UserDTO userDTO) {
        if (user == null) {
            return null;
        }

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUserRole(user.getUserRole().getValue());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }

    @Override
    public UserDTO findDtoById(int id) {
        return mapDTOFromEntity(findOne(id), new UserDTO());
    }

    @Override
    public User mapEntityFromDTO(UserDTO dto, User user) {

        user.setId(dto.getId());

        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            user.setPassword(dto.getPassword());
        }

        user.setName(dto.getName());
        user.setUserRole(dto.getUserRole() != null ? UserRole.valueOf(dto.getUserRole()) : UserRole.USER);
        user.setEmail(dto.getEmail());

        return user;
    }

    protected User createNewInstanceOfEntityClass() {

        User user = null;

        try {
            user = getEntityClass().newInstance();
        } catch (Exception e) {
        }

        return user;
    }

    protected Class<User> getEntityClass() {
        return UtilsReflection.getParameterClazzOfType(this.getClass().getGenericSuperclass(), 0);
    }

    protected Class<UserDTO> getEntityDTOClass() {
        return UtilsReflection.getParameterClazzOfType(this.getClass().getGenericSuperclass(), 1);
    }
}