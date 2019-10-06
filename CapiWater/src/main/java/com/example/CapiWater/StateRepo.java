package com.example.CapiWater;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  StateRepo extends JpaRepository<State, Long> {

	@Query("SELECT s FROM State s WHERE name = :name")
	Optional<State> StateExists(String name);
}
