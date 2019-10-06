package com.example.CapiWater;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface  HouseRepo extends JpaRepository<House, Long> {
	@Query("SELECT h FROM House h WHERE h.user.uuid = :uuid")
	Page<House> HouseExists(Pageable page,String uuid);
	
	@Query("SELECT h FROM House h WHERE hid = :hid")
	Optional<House> HouseIDExists(long hid);
	
	
}
