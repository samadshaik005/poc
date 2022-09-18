package com.nagarro.vaccinationcenter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nagarro.vaccinationcenter.dto.Citizen;
import com.nagarro.vaccinationcenter.dto.RequiredResponse;
import com.nagarro.vaccinationcenter.entity.VaccinationCenter;
import com.nagarro.vaccinationcenter.repository.CenterRepo;
import com.nagarro.vaccinationcenter.service.VaccinationCenterService;

@RestController
@RequestMapping("/vaccinationcenter")
public class Vaccinationcontroller {
	
	
	@Autowired
	private CenterRepo centerRepo;
	
	@Autowired
	private VaccinationCenterService VaccinationCenterService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping(path ="/add")
	public ResponseEntity<VaccinationCenter> addCenter(@RequestBody VaccinationCenter vaccinationCenter) {
		
		VaccinationCenter vaccinationCenterAdded = VaccinationCenterService.saveCenter(vaccinationCenter);
		return new ResponseEntity<>(vaccinationCenterAdded, HttpStatus.OK);
	}
	
	@DeleteMapping(path ="/remove/{id}")
	public String removeCenter(@PathVariable Integer id) {	
		String msg = VaccinationCenterService.removeCenter(id);
		return msg;
	}
	
	@GetMapping(path = "/id/{id}")
	//@HystrixCommand(fallbackMethod="handleCitizenDowntime")  
	public ResponseEntity<RequiredResponse> getAllDataBasedonCenterId(@PathVariable Integer id){
		RequiredResponse requiredResponse =  new RequiredResponse();
		VaccinationCenter center  = centerRepo.findById(id).get();
		requiredResponse.setCenter(center);
		
		List<Citizen> listOfCitizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/"+id, List.class);
		requiredResponse.setCitizens(listOfCitizens);
		return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
	}
	
	//if citizen service is down ,then above method throws exception ,this method atleast return the vaccination data.
	public ResponseEntity<RequiredResponse> handleCitizenDowntime(@PathVariable Integer id){
		
		RequiredResponse requiredResponse =  new RequiredResponse();
		VaccinationCenter center  = centerRepo.findById(id).get();
		requiredResponse.setCenter(center);
		return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);		
	}

	//returns only vaccination center data based on id
	@GetMapping(path = "/{id}")
	public ResponseEntity<RequiredResponse> getCenterDataBasedonCenterId(@PathVariable Integer id){
		RequiredResponse requiredResponse =  new RequiredResponse();
		VaccinationCenter center  = centerRepo.findById(id).get();
		requiredResponse.setCenter(center);
		return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "/allcenters")
	public List<VaccinationCenter> getCenterAllData(){
		RequiredResponse requiredResponse =  new RequiredResponse();
		List<VaccinationCenter> centers  = centerRepo.findAll();
		return centers;
	}
	
}
