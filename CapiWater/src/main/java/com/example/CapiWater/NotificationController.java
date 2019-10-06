package com.example.CapiWater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
public class NotificationController {
	@Autowired
	private NotificationRepo notificationRepo;
	@Autowired
	private TapRepo tapRepo;
	
	@GetMapping(value = "/notification/{hid}", produces = "application/json")
	public List<Notification> getReportDailySum(@PathVariable long hid) throws InvalidConfigurationPropertyValueException
    {
		return notificationRepo.NotiExistByHID(hid);
		
	}
	
	@PutMapping(value = "/notification/{tid}/{date}/{duration}", produces = "application/json")
	public ResponseEntity<String> putReportRecord(@PathVariable long tid, @PathVariable("date") String date, @PathVariable int duration ) throws InvalidConfigurationPropertyValueException, ParseException {
		Date newDate;
		Notification noti;
		newDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmX").parse(date);
		Optional<Tap> tapStock = tapRepo.TapExistsById(tid);
		 
		if(!tapStock.isPresent()) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND," Resource not found");	
		 }
		 
		noti = new Notification(duration,newDate,tapStock.get());
		try {
			notificationRepo.saveAndFlush(noti);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		 
		return ResponseEntity.ok("Success");
	}
	
	 @GetMapping("/notification")
	 public List<Notification> getAllTaps() {
	   return notificationRepo.findAll();
	 }
	 

}
