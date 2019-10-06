package com.example.CapiWater;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {
	
	 @Autowired
	 private UserRepo userRepository;

	 @PostMapping("/users")
	 public User createUser(@Valid @RequestBody User user) {
	   return userRepository.saveAndFlush(user);
	 }
	  
	 @GetMapping("/users")
	 public List<User> getAllUsers() {
	   return userRepository.findAll();
	 }
	 @GetMapping("/users/{uuid}")
	 public ResponseEntity<User> getUsersByUUId(@PathVariable(value = "uuid") String uuid) throws InvalidConfigurationPropertyValueException{
		 Optional<User> stock = userRepository.userExists(uuid);
		 if(!stock.isPresent()) {
			 throw new InvalidConfigurationPropertyValueException("uuid",uuid,"Resource not found");
		 }
		 return ResponseEntity.ok().body(stock.get());
	 }
	 
	 @DeleteMapping("/users/{id}")
	 public void deleteUser(@PathVariable long id) {
		 userRepository.deleteById(id);
		}


}
