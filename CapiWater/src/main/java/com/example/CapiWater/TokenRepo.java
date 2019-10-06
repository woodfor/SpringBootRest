package com.example.CapiWater;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepo extends JpaRepository<Token, Long>{

	@Query("SELECT t FROM Token t WHERE tid = :id")
	Optional<Token> tokenExists(long id);
}
