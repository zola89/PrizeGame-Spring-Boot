package com.lazar.prizegame.model.enums;

public enum UserRole {
	
	ADMIN("Admin"),

    USER("User"),

    SYSTEM_ADMIN("System Admin");

    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private String value;

    UserRole(String value) {
        this.value = value;
    }

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
