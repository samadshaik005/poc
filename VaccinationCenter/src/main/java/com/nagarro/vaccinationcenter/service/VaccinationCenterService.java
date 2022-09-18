package com.nagarro.vaccinationcenter.service;

import com.nagarro.vaccinationcenter.entity.VaccinationCenter;


public interface VaccinationCenterService {

	VaccinationCenter saveCenter(VaccinationCenter vaccinationCenter);

	String removeCenter(Integer id);

}
