package com.lazar.prizegame.model.enums;

public enum PrizeType {
	
	CASH("Cash"),
	GIFT_CARD("Voucher"),
	HOLIDAY("Vacation for 2"),
	MOBILE_PHONE("Mobile Phone"),
	SHIRT("T-shirt");
	
	private String value;

	private PrizeType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static PrizeType getByValue(String s) {

		PrizeType[] values = PrizeType.values();

		for (PrizeType value : values) {
			if (value.getValue().equalsIgnoreCase(s)) {
				return value;
			}
		}

			return null;
		}
	
	
}
