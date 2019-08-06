package com.lazar.prizegame.dto;


import com.lazar.prizegame.dto.core.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodeDTO implements EntityDTO {

    private int id;
    private String prizeCode;
    private String prizeType;
    private Timestamp prizeTime;
    private int userId;



}
