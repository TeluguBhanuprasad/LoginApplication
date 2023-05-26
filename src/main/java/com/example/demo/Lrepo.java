package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Lrepo  extends JpaRepository<LEntity, Integer> {

//	
//	String  findByUsername(String username);
//	String  findByPassword(String password);
//	
	
	
	List<LEntity> findByUsername(String username);
	List<LEntity> findByPassword(String password);
}
