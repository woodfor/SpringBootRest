package com.example.CapiWater;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface TapRepo extends JpaRepository<Tap, Long> {
	@Query("SELECT t FROM Tap t WHERE t.area.aid = :aid")
	List<Tap> TapExists(long aid);
	@Query("SELECT t FROM Tap t WHERE t.tid = :tid")
	Optional<Tap> TapExistsById(long tid);
	@Query("SELECT t.token FROM Tap t WHERE t.tid = :tid")
	String getToken(long tid);
	@Query("SELECT t FROM Tap t WHERE t.area.house.hid = :hid")
	List<Tap> TapExistsByHouse(long hid);
	@Query("SELECt t.area FROM Tap t WHERE t.tid = :tid")
	Optional<Area> getAreaFromTid(long tid);
}
