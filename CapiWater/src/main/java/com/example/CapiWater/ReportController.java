package com.example.CapiWater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
public class ReportController {
	@Autowired
	private ReportRepo reportRepo;
	@Autowired
	private AreaRepo areaRepo;
	@Autowired
	private TapRepo tapRepo;

	@PostMapping("/report")
	public Report createUser(@Valid @RequestBody Report report) {
		return reportRepo.saveAndFlush(report);
	}

	@GetMapping(value = "/report/sum/date/{hid}/{date}", produces = "application/json")
	public int getReportDailySum(@PathVariable long hid,@PathVariable("date") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) throws InvalidConfigurationPropertyValueException {
		Optional<Integer> stock = reportRepo.getDailySumUsage(hid,date);
		return 	stock.isPresent()?stock.get():0;
	
		
	}
	
	@GetMapping(value = "/report/sum/month/{hid}/{date}", produces = "application/json")
	public int getReportMonthlySum(@PathVariable long hid,@PathVariable("date") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) throws InvalidConfigurationPropertyValueException {
		Optional<Integer> stock = reportRepo.getMonthlySumUsage(hid,date);
		return 	stock.isPresent()?stock.get():0;
	}
	@GetMapping(value = "/report/sum/year/{hid}/{date}", produces = "application/json")
	public int getReportYearlySum(@PathVariable long hid,@PathVariable("date") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) throws InvalidConfigurationPropertyValueException {
		Optional<Integer> stock = reportRepo.getYearlySumUsage(hid, date);
		return 	stock.isPresent()?stock.get():0; 
	}
	
	@GetMapping(value = "/report/date/{hid}/{date}", produces = "application/json")
	public List<DailyResult> getReportDaily(@PathVariable long hid,@PathVariable("date") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) throws InvalidConfigurationPropertyValueException {

		return reportRepo.getDailyReport(hid,date);
	}
	
	@GetMapping(value = "/report/month/{hid}/{date}", produces = "application/json")
	public List<MonthlyResult> getReportMonthly(@PathVariable long hid,@PathVariable("date") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) throws InvalidConfigurationPropertyValueException {

		return reportRepo.getMonthlyReport(hid, date);
	}
	@GetMapping(value = "/report/year/{hid}/{date}", produces = "application/json")
	public List<YearlyResult> getReportYearly(@PathVariable long hid,@PathVariable("date") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) throws InvalidConfigurationPropertyValueException {

		return reportRepo.getYearlyReport(hid, date);
	}
	
	@PutMapping(value = "/report/{tid}/{date}/{usage}/{duration}", produces = "application/json")
	public Report putReportRecord(@PathVariable long tid, @PathVariable("date") 
    String date, @PathVariable int usage,@PathVariable int duration ) throws InvalidConfigurationPropertyValueException, ParseException {
		Date newDate;
		Report report;
		newDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmX").parse(date);
		 Optional<Area> areaStock = tapRepo.getAreaFromTid(tid);
		 Optional<Tap> tapStock = tapRepo.TapExistsById(tid);
		 if(!areaStock.isPresent()) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND," Resource not found");	
		 }
		 if(!tapStock.isPresent()) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND," Resource not found");	
		 }
		report = new Report(areaStock.get(),tapStock.get(),newDate,usage,duration);
		 
		return reportRepo.saveAndFlush(report);
	}
	
	/*
	 * @GetMapping(value = "/house/{uuid}",produces = "application/json") public
	 * ResponseEntity<House> getHouseByUUID(@PathVariable String uuid) throws
	 * InvalidConfigurationPropertyValueException {
	 * 
	 * Page<House> houseStock = houseRepo.HouseExists(PageRequest.of(0,1),uuid);
	 * List<House> houses = houseStock.getContent(); if(houses.isEmpty()) { throw
	 * new InvalidConfigurationPropertyValueException("House with uuid: "
	 * ,uuid," Resource not found"); } return
	 * ResponseEntity.ok().body(houses.get(0));
	 * 
	 * 
	 * 
	 * List<House> houses =
	 * entityManager.createQuery("SELECT h FROM House h WHERE h.user.uuid = :uuid",
	 * House.class) .setParameter("uuid", uuid) .setMaxResults(1).getResultList();
	 * 
	 * 
	 * }
	 */

	 @GetMapping(value = "/report/MonthlyRank",produces = "application/json")
	 public List<RankResult> getRanks() {
		 Date today = Calendar.getInstance().getTime();
		 return reportRepo.getRanks(today);
	 }
	
	@GetMapping("/report")
	public List<Report> getReports() {
		return reportRepo.findAll();
	}
	
	@DeleteMapping("/report/{id}")
	public void deleteReport(@PathVariable long id) {
		reportRepo.deleteById(id);
	}
}
