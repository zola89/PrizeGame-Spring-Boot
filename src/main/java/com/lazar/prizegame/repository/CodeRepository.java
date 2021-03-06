package com.lazar.prizegame.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lazar.prizegame.model.Code;

public interface CodeRepository extends CrudRepository<Code, Integer> {
	
	@Query("select c from Code c where c.prizeCode = ?1")
	Code findByPrizeCode(String prizeCode);
	
	@Query("select c from Code c where c.user.id= ?1")
	Iterable<Code> findByUserId(int userId);

}