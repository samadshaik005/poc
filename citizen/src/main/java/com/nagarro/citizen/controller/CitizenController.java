package com.nagarro.citizen.controller;

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

import com.nagarro.citizen.entity.Citizen;
import com.nagarro.citizen.repository.CitizenRepo;
import com.nagarro.citizen.service.CitizenService;



@RestController
	@RequestMapping("/citizen")
	public class CitizenController {
		
		@Autowired
		private CitizenRepo repo; 
		
		@Autowired
		private CitizenService citizenservice;

		@GetMapping(path ="/test")
		public ResponseEntity<String> test() {
			return new ResponseEntity<>("hello", HttpStatus.OK);
		}
		
		@GetMapping(path ="/id/{id}")
		public ResponseEntity<java.util.List<Citizen>> getById(@PathVariable Integer id) {
			
			List<Citizen> listCitizen = repo.findByVaccinationCenterId(id);
			return new ResponseEntity<>(listCitizen, HttpStatus.OK);
		}
		
		@PostMapping(path ="/add")
		public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen) {
			
			Citizen citizen = citizenservice.saveCitizen(newCitizen);
			return new ResponseEntity<>(citizen, HttpStatus.OK);
		}
		
		@DeleteMapping(path ="/remove/{id}")
		public String removeCitizen(@PathVariable Integer id) {		
			String msg = citizenservice.removeCitizen(id);
			return msg;
		}
		
		@GetMapping(path ="/allcitizens")
		public List<Citizen> allCitizens() {		
			List<Citizen> Citizens = repo.findAll();
			return Citizens;
		}
}
