package com.example.CapiWater;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface SuburbRepo extends JpaRepository<Suburb, Long> {
	@Query("SELECT s FROM Suburb s WHERE name = :name and postcode = :postcode and s.state.name = :statename")
	Optional<Suburb> SuburbExists(String name,String postcode, String statename);
}
