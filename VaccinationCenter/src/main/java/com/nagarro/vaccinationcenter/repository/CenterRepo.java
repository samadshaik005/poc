package com.nagarro.vaccinationcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.vaccinationcenter.entity.VaccinationCenter;

@Repository
public interface CenterRepo extends JpaRepository<VaccinationCenter, Integer>{

}
