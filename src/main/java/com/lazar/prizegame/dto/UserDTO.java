package com.lazar.prizegame.dto;

import com.lazar.prizegame.dto.core.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements EntityDTO {

    private int id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String userRole;
    private String address;




}