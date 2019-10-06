package com.example.CapiWater;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long>  {
	@Query("SELECT n FROM Notification n WHERE n.tap.area.house.hid = :hid order by n.dateTime DESC")
	List<Notification> NotiExistByHID(long hid);
}
