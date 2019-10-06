package com.example.CapiWater;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	@Query("SELECT u FROM User u WHERE uuid = :uuid")
	Optional<User> userExists(String uuid);
}
