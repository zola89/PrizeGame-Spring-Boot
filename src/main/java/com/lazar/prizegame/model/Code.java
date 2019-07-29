package com.lazar.prizegame.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.json.JSONObject;

import com.lazar.prizegame.model.enums.PrizeType;

@Entity
@Table(name = "code")
public class Code {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "prize_code", nullable = false, unique = true)
	private String prizeCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "prize_type", nullable = false)
	private PrizeType prizeType;

	@Column(name = "prize_time")
	private Timestamp prizeTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public Code() {
	}

	public Code(String prizeCode, String prizeType, User user) {
		this.prizeCode = prizeCode;
		this.prizeType = PrizeType.getByValue(prizeType);
		this.user = user;
	}

	public Code(String prizeCode, String prizeType, User user, Timestamp prizeTime) {
		this.prizeCode = prizeCode;
		this.prizeType = PrizeType.getByValue(prizeType);
		this.user = user;
		this.prizeTime = prizeTime;
	}

	// prizeCode
	public String getPrizeCode() {
		return prizeCode;
	}

	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public String toString() {
		String info = "";

		JSONObject jsonInfo = new JSONObject();
		jsonInfo.put("prizeCode", this.prizeCode);
		if(this.prizeType != null) {
			jsonInfo.put("prizeType", this.prizeType.getValue());
		}

		if (this.prizeTime != null) {
			jsonInfo.put("prizeTime", this.prizeTime);
		}
		JSONObject userObj = new JSONObject();
		userObj.put("name", this.user.getName());

		jsonInfo.put("user", userObj);

		info = jsonInfo.toString();
		return info;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PrizeType getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(PrizeType prizeType) {
		this.prizeType = prizeType;
	}

	public Timestamp getPrizeTime() {
		return prizeTime;
	}

	public void setPrizeTime(Timestamp prizeTime) {
		this.prizeTime = prizeTime;
	}
}
