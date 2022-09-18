package com.nagarro.citizen.service;

import com.nagarro.citizen.entity.Citizen;

public interface CitizenService {

	Citizen saveCitizen(Citizen newCitizen);

	String removeCitizen(Integer id);

}
