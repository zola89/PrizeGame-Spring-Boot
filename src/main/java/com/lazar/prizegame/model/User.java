package com.lazar.prizegame.model;



import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "user")
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name ="password", nullable= false)
    private String password;

    @Column(name = "phone")
    private String phone;
    
    @Column(name = "email", nullable= false, unique = true)
    private String email;
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name ="address")
    private String address;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Code> codes;
    
    public Set<Code> getCodes() {
		return codes;
	}

	public void setCodes(Set<Code> codes) {
		this.codes = codes;
	}

	public User() {
        super();
    }

    public User(int id, String name, String password,String phone, String email, String address) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email= email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String toString(){
    	String info = "";
    	
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("id",this.getId());
        
        jsonInfo.put("name", this.getName());
        jsonInfo.put("phone", this.getPhone());
        jsonInfo.put("email", this.getEmail());
        jsonInfo.put("address", this.getAddress());
        JSONArray productArray = new JSONArray();
        if(this.codes != null){
            this.codes.forEach(code->{
                //JSONObject subJson = new JSONObject();
                //subJson.put(new JSONObject(code.toString()));
                productArray.put(new JSONObject(code.toString()));
            });
        }
        jsonInfo.put("products", productArray);
        info = jsonInfo.toString();
        return info;
    }

}