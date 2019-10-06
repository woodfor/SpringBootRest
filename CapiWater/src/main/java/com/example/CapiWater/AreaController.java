package com.example.CapiWater;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class AreaController {
	@Autowired
	 private AreaRepo areaRepo;
	@Autowired
	 private HouseRepo houseRepo;
	
	 @PostMapping("/area")
	 public Area createUser(@Valid @RequestBody Area area) {
	   return areaRepo.saveAndFlush(area);
	 }
	  
	 @PutMapping(value = "/area/{hid}/{name}", produces = "application/json")
	 public Area insertAreaByName(@PathVariable long hid,@PathVariable String name) {
		 Area area = new Area();
		 Optional<House> houseStock = houseRepo.HouseIDExists(hid);
		
		  if(!houseStock.isPresent()) { 
			  throw new ResponseStatusException(HttpStatus.NOT_FOUND," Resource not found");
		  }	
		  else {
			  area.setHouse(houseStock.get());
			  area.setName(name);
		  }
	   return areaRepo.saveAndFlush(area);
	 }
//	 @PutMapping(value = "/area/{hid}/{name}", produces = "application/json")
//	 public Area insertAreaByName(@PathVariable long hid,@PathVariable String name) {
//		 Area area = new Area();
//		 Page<House> houseStock = houseRepo.HouseIDExists(PageRequest.of(0,1),hid);
//		 List<House> houses = houseStock.getContent();
//		  if(houses.isEmpty()) { throw new
//		  InvalidConfigurationPropertyValueException("House with uuid: "
//		  ,hid," Resource not found"); }
//		  else {
//			  area.setHouse(houses.get(0));
//			  area.setName(name);
//		  }
//	   return areaRepo.saveAndFlush(area);
//	 }
	 
	 @GetMapping(value = "/area/{hid}", produces = "application/json")
	 public List<Area> getAreaByHouse(@PathVariable long hid) {
	   return areaRepo.AreaExists(hid);
	 }
	 
	 @GetMapping("/area")
	 public List<Area> getAllUsers() {
	   return areaRepo.findAll();
	 }
	
	
	 @DeleteMapping("/area/{id}")
	 public void deleteArea(@PathVariable long id) {
		 areaRepo.deleteById(id);
		}
	 
	 @PutMapping("/area/update/{id}/{name}")
	 public ResponseEntity<String> updateArea(@PathVariable long id, @PathVariable String name) {
		 Optional<Area> stock = areaRepo.AreaExistsById(id);
		 if(stock.isPresent()) {
			 Area area =  stock.get();
			 area.setName(name);
			 areaRepo.saveAndFlush(area);
			 return ResponseEntity.ok("Success");
		 }else {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND," Resource not found");			   
		 }
	 }
}
