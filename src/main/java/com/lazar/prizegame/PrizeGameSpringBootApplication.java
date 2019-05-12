package com.lazar.prizegame;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.lazar.prizegame.model.Code;
import com.lazar.prizegame.model.User;
import com.lazar.prizegame.repository.CodeRepository;
import com.lazar.prizegame.repository.UserRepository;


@SpringBootApplication
public class PrizeGameSpringBootApplication implements CommandLineRunner{
	
	@Autowired
	CodeRepository codeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PrizeGameSpringBootApplication.class, args);
	}
	
	
	@Override
    public void run(String... arg0) throws Exception {
    	//clearData();
    	//saveData();
    	//showData();
    }
    
    @Transactional
    private void clearData(){
    	userRepository.deleteAll();
    	codeRepository.deleteAll();
    }
    
    @Transactional
    private void saveData(){
    	saveDataWithApproach1();
        // saveDataWithApproach2();
    }
    
    private void saveDataWithApproach1(){
        User user1 = new User(13,"Lazar", "12345", "012312412", "zola89@gmail.com", "27Marta 28");
        User user2= new User(14,"Nada","12345", "0123233412", "nbogdano@calliduscloud.com", "27 Marta 28");
        
        Code code1 = new Code("12345462", "zamena", user1);
        Code code2 = new Code("12345466", "letovanje", user1,new Timestamp(System.currentTimeMillis()));
        

        Code code3 = new Code("12345423", "zamena", user2);
        Code code4 = new Code("12345432", "automobil", user2);
        
        //Code code5 = new Code("12345678", "automobil", user2);
        //codeRepository.save(code5);
        user1.setCodes(new HashSet<Code>(){{
            add(code1);
            add(code2);
        }});
        
        user2.setCodes(new HashSet<Code>(){{
            add(code3);
            add(code4);
        }});
        
        // save users
        userRepository.save(user1);
        userRepository.save(user2);
    }
    
    @Transactional
    private void showData(){
    	// get All data
    	Iterable<User> userList = userRepository.findAll();
        Iterable<Code> codeList = codeRepository.findAll();
         
        System.out.println("===================User List:==================");
        userList.forEach(System.out::println);
         
        System.out.println("===================Code List:==================");
        codeList.forEach(System.out::println);
    }
}
