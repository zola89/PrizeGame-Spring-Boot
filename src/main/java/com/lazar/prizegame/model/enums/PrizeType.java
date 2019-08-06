package com.lazar.prizegame.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PrizeType {
	
	CASH("Cash"),
	GIFT_CARD("Voucher"),
	HOLIDAY("Vacation for 2"),
	MOBILE_PHONE("Mobile Phone"),
	SHIRT("T-shirt");
	
	private String value;
	
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
