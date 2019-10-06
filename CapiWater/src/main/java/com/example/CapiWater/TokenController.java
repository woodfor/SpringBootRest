package com.example.CapiWater;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TokenController {

	 @Autowired
	 private TokenRepo tokenRepo;
	 
	 @PostMapping("/token")
	 public Token createToken(@Valid @RequestBody Token token) {
	   return tokenRepo.saveAndFlush(token);
	 }
	  
	 @GetMapping("/token/all")
	 public List<Token> getAllToken() {
	   return tokenRepo.findAll();
	 }
	 
	 @GetMapping(value = "/token", produces = "application/json")
	 public ResponseEntity<Token> getUsersByUUId() throws InvalidConfigurationPropertyValueException{
		 Optional<Token> stock = tokenRepo.tokenExists(2);
		 if(!stock.isPresent()) {
			 throw new InvalidConfigurationPropertyValueException("id",2,"Resource not found");
		 }
		 return ResponseEntity.ok().body(stock.get());
	 }
	 
	 @RequestMapping(value = "/token", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	 public ResponseEntity<Object> updateProduct( @RequestBody Token token) { 
		
		 Optional<Token> stock = tokenRepo.tokenExists(2);
		 if(!stock.isPresent()) {
			 throw new InvalidConfigurationPropertyValueException("id",2,"Resource not found");
		 }else {
			 Token entity = stock.get();
			 entity.setToken(token.getToken());
			 tokenRepo.save(entity);
			 return new ResponseEntity<>("token is updated successsfully", HttpStatus.OK);
		 }
		
	   }  
}
