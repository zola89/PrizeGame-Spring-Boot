package com.lazar.prizegame.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="code")
public class Code {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "prize_code", nullable = false, unique = true)
    private String prizeCode;
    
	@Column(name= "prize_time")
	private Timestamp prizeTime;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    
    public Code(){
    }
    
    public Code(String prizeCode, User user){
    	this.prizeCode = prizeCode;
    	this.user = user;
    }
    
    // prizeCode
    public String getPrizeCode() {
        return prizeCode;
    }
    
    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode;
    }
    
    public void setUser(User user){
    	this.user = user;
    }
    
    public User getUser(){
    	return this.user;
    }
    
    public String toString(){
    	String info = "";
    	
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("prizeCode",this.prizeCode);
        
        JSONObject userObj = new JSONObject();
        userObj.put("name", this.user.getName());
        jsonInfo.put("user", userObj);
        
        info = jsonInfo.toString();
        return info;
    }
}
