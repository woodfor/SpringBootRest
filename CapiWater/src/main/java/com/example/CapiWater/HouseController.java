package com.example.CapiWater;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
public class HouseController {
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	 private HouseRepo houseRepo;
	@Autowired
	 private UserRepo userRepo;
	@Autowired
	 private SuburbRepo suburbRepo;
	@Autowired
	 private StateRepo stateRepo;
	 @PostMapping(value = "/house", consumes = "application/json", produces = "application/json")
	 public House createHouse(@RequestBody HouseCreation house) {
	    User user = house.getUser();
	    House thishouse = house.getHouse();
	    Suburb suburb = house.getSuburb();
	    State state =  house.getState();
	    
	    Optional<State> stock = stateRepo.StateExists(state.getName());
	    if(!stock.isPresent()) {
	    	state = stateRepo.saveAndFlush(state);
	    }else {
	    	state = stock.get();
	    }
	    
	    Optional<Suburb> subStock = suburbRepo.SuburbExists(suburb.getName(), suburb.getPostcode(), state.getName());
	    if(!subStock.isPresent()) {
	    	suburb.setState(state);
			suburb = suburbRepo.saveAndFlush(suburb);	    	
	    }else {
	    	suburb = subStock.get();
	    }
	    Optional<User> userStock = userRepo.userExists(user.getUUID());
		 if(!userStock.isPresent()) {
			 user = userRepo.saveAndFlush(user);
		 }else {
			 user = userStock.get();
		 }
	   				
		thishouse.setUser(user);
		thishouse.setSuburb(suburb);
		 
		return houseRepo.saveAndFlush(thishouse);
	 }
	 
	 @GetMapping(value = "/house/{uuid}",produces = "application/json")
	 public ResponseEntity<House> getHouseByUUID(@PathVariable String uuid)  throws InvalidConfigurationPropertyValueException {
		
		  Page<House> houseStock = houseRepo.HouseExists(PageRequest.of(0,1),uuid);
		  List<House> houses = houseStock.getContent();
		  if(houses.isEmpty()) { throw new
		  ResponseStatusException(HttpStatus.NOT_FOUND," Resource not found"); } 
		  return ResponseEntity.ok().body(houses.get(0));
		 
		  
		/*
		 * List<House> houses =
		 * entityManager.createQuery("SELECT h FROM House h WHERE h.user.uuid = :uuid",
		 * House.class) .setParameter("uuid", uuid) .setMaxResults(1).getResultList();
		 */
		
	 }
	  

	 @PutMapping("/house/update/{id}/{name}/{nop}")
	 public House updateArea(@PathVariable long id, @PathVariable String name,@PathVariable int nop) {
		 Optional<House> stock = houseRepo.HouseIDExists(id);
		 if(stock.isPresent()) {
			 House house =  stock.get();
			 house.setName(name);
			 house.setNop(nop);
			return houseRepo.saveAndFlush(house);
		 }else {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND," Resource not found");			   
		 }
	 }
	 
	 @GetMapping("/house")
	 public List<House> getAllHouses() {
	   return houseRepo.findAll();
	 }
	
	 @DeleteMapping("/house/{id}")
	 public void deletehouse(@PathVariable long id) {
		 houseRepo.deleteById(id);
		}
}
