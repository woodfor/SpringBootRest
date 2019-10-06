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
public class SuburbController {
	@Autowired
	 private SuburbRepo suburbRepo;
	 @PostMapping("/suburb")
	 public Suburb createSuburb(@Valid @RequestBody Suburb suburb) {
	   return suburbRepo.saveAndFlush(suburb);
	 }
	  
	 @GetMapping("/suburb")
	 public List<Suburb> getAllUsers() {
	   return suburbRepo.findAll();
	 }
	
	 @DeleteMapping("/suburb/{id}")
	 public void deleteSuburb(@PathVariable long id) {
		 suburbRepo.deleteById(id);
		}
}
