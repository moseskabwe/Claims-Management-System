package com.simplehomeinsurance.claims_management_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.AdjusterDAO;
import com.simplehomeinsurance.claims_management_system.entity.Adjuster;

@Service
public class AdjusterService {
	
	@Autowired	
	private AdjusterDAO adjusterDAO;
	
	@Transactional
	public List<Adjuster> getAdjustersList() {
		
		return adjusterDAO.getAdjustersList();	
	}
	
	@Transactional
	public Adjuster getAdjuster(int adjusterNumber) {
		
		return adjusterDAO.getAdjuster(adjusterNumber);		
	}
	
	@Transactional
	public void saveAdjuster(Adjuster adjuster) {
		
		adjusterDAO.saveAdjuster(adjuster);		
	}
}
