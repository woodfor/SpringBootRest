package com.example.CapiWater;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface  AreaRepo extends JpaRepository<Area, Long>{
	@Query("SELECT a FROM Area a WHERE a.house.hid = :hid")
	List<Area> AreaExists(long hid);
	@Query("SELECT a FROM Area a WHERE a.aid = :aid")
	Optional<Area> AreaExistsById(long aid);
	
}
