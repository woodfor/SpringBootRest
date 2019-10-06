package com.example.CapiWater;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
public class TapController {
	 @Autowired
	 private TapRepo tapRepo;
	 @Autowired
	 private AreaRepo areaRepo;

	 @PostMapping("/tap")
	 public Tap createTap(@Valid @RequestBody Tap tap) {
	   return tapRepo.saveAndFlush(tap);
	 }
	 
	 @PutMapping(value = "/tap/{aid}/{name}/{address}/{token}", produces = "application/json")
	 public Tap insertTapByName(@PathVariable long aid,@PathVariable String name,@PathVariable String address, @PathVariable String token) {
		 
		 Optional<Area> stock = areaRepo.AreaExistsById(aid);
		 if(!stock.isPresent()) {
			 throw new InvalidConfigurationPropertyValueException("aid",aid,"Resource not found");
		 }else {
			 Area area;
			 area = stock.get();
			 Tap tap = new Tap(name,address,area,token);
			 return tapRepo.saveAndFlush(tap);
		 }
		 
	 }
	 
	 
	 @PutMapping(value = "/tap/token/{hid}/{token}", produces = "application/json")
	 public void updateToken(@PathVariable long hid, @PathVariable String token) {
		 List<Tap> taps = tapRepo.TapExistsByHouse(hid);
		 if(!taps.isEmpty()) {
			 for(Tap tap : taps) {
					tap.setToken(token);
					tapRepo.saveAndFlush(tap);
				 } 
		 }else {
			 throw new InvalidConfigurationPropertyValueException("house",hid,"Resource not found");
		 }
		 
	 }
	 
	 @GetMapping(value = "/tap/{aid}", produces = "application/json")
	 public List<Tap> getTapByArea(@PathVariable long aid) {
	   return tapRepo.TapExists(aid);
	 }
	 
	 @GetMapping(value = "/tap/token/{tid}", produces = "application/json")
	 public String getToken(@PathVariable long tid) {
	   return tapRepo.getToken(tid);
	 }
	 
	 @PutMapping("/Tap/update/{id}/{name}")
	 public ResponseEntity<String> updateArea(@PathVariable long id, @PathVariable String name) {
		 Optional<Tap> stock = tapRepo.TapExistsById(id);
		 if(stock.isPresent()) {
			 Tap tap =  stock.get();
			 tap.setName(name);
			 tapRepo.saveAndFlush(tap);
			 return ResponseEntity.ok("Success");
		 }else {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND," Resource not found");			   
		 }
	 }
	 
	 
	 
	 @GetMapping("/tap")
	 public List<Tap> getAllTaps() {
	   return tapRepo.findAll();
	 }
	 
	 @DeleteMapping("/tap/{id}")
	 public void deleteTap(@PathVariable long id) {
		 tapRepo.deleteById(id);
		}
}
