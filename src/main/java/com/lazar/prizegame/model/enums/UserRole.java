package com.lazar.prizegame.model.enums;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserRole {
	
	ADMIN("Admin"),

    USER("User"),

    SYSTEM_ADMIN("System Admin");

    private String value;

    public static UserRole getByValue(String s) {

        UserRole[] values = UserRole.values();

        for (UserRole value : values) {
            if (value.getValue().equalsIgnoreCase(s)) {
                return value;
            }
        }

        return null;
    }

}
