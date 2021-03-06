package com.lazar.prizegame;

import com.lazar.prizegame.model.Code;
import com.lazar.prizegame.model.User;
import com.lazar.prizegame.repository.CodeRepository;
import com.lazar.prizegame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;


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
    	//saveDataWithApproach1();
        // saveDataWithApproach2();
    }
    
    private void saveDataWithApproach1(){
//        User user1 = new User(13, "Lazar", "12345", "012312412", "zola89@gmail.com", "27Marta 28", UserRole.ADMIN);
//        User user2= new User(14 , "Nada","12345", "0123233412", "nbogdano@calliduscloud.com", "27 Marta 28", UserRole.ADMIN);
//
//        Code code1 = new Code(145,"12345462", PrizeType.CASH, new Timestamp(System.currentTimeMillis()) , user1);
//        Code code2 = new Code(146,"12345466", PrizeType.CASH, new Timestamp(System.currentTimeMillis()), user1);
//        Code code3 = new Code(147,"12345423", PrizeType.CASH, new Timestamp(System.currentTimeMillis()) , user2);
//        Code code4 = new Code(148,"12345432", PrizeType.CASH, new Timestamp(System.currentTimeMillis()) , user2);
//
        //Code code5 = new Code("12345678", "automobil", user2);
        //codeRepository.save(code5);
//        user1.setCodes(new HashSet<Code>(){{
//            add(code1);
//            add(code2);
//        }});
//
//        user2.setCodes(new HashSet<Code>(){{
//            add(code3);
//            add(code4);
//        }});
//
//        // save users
//        userRepository.save(user1);
//        userRepository.save(user2);
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
