package com.example.CapiWater;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StateController {
	@Autowired
	 private StateRepo stateRepo;
	 @PostMapping("/state")
	 public State createUser(@Valid @RequestBody State state) {
	   return stateRepo.saveAndFlush(state);
	 }
	  
	 @GetMapping("/state")
	 public List<State> getAllUsers() {
	   return stateRepo.findAll();
	 }
	
	 @DeleteMapping("/state/{id}")
	 public void deleteState(@PathVariable long id) {
		 stateRepo.deleteById(id);
		}
}
