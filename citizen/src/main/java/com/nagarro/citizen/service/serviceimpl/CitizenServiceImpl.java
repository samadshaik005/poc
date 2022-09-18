package com.nagarro.citizen.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.citizen.entity.Citizen;
import com.nagarro.citizen.repository.CitizenRepo;
import com.nagarro.citizen.service.CitizenService;


@Service
public class CitizenServiceImpl implements CitizenService {
	
	@Autowired
	private CitizenRepo repo;

	@Override
	public Citizen saveCitizen(Citizen newCitizen) {
		// TODO Auto-generated method stub
		Citizen citizen = repo.save(newCitizen);
		
		return citizen;
	}

	@Override
	public String removeCitizen(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		return "successfully deleted";
	}

}
