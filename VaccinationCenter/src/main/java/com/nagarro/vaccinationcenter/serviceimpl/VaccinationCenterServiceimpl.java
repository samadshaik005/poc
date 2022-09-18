package com.nagarro.vaccinationcenter.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.vaccinationcenter.entity.VaccinationCenter;
import com.nagarro.vaccinationcenter.repository.CenterRepo;
import com.nagarro.vaccinationcenter.service.VaccinationCenterService;

@Service
public class VaccinationCenterServiceimpl implements VaccinationCenterService {
	
	@Autowired
	private CenterRepo centerRepo;

	@Override
	public VaccinationCenter saveCenter(VaccinationCenter vaccinationCenter) {
		// TODO Auto-generated method stub
		
		VaccinationCenter vaccinationCenterAdded = centerRepo.save(vaccinationCenter);
		return vaccinationCenterAdded;
		
	}

	@Override
	public String removeCenter(Integer id) {
		// TODO Auto-generated method stub
		centerRepo.deleteById(id);
		return "successfully deleted";
		
	}

}
