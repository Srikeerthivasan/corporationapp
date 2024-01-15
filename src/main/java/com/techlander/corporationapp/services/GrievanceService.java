package com.techlander.corporationapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.techlander.corporationapp.models.Grievance;
import com.techlander.corporationapp.repositories.GrievanceRepository;

@Service
public class GrievanceService {

	@Autowired
	private GrievanceRepository grievanceRepository;
	
	//Returns the list of grievances from the database
	public List<Grievance> getGrievances(){
		return grievanceRepository.findAll();
	}
	
	//Save new vehicle to the database
	public void save(Grievance grievance) {
		grievanceRepository.save(grievance);
	}
	
	//get by id
	public Grievance findById(int id) {
		Grievance grievance = grievanceRepository.findById(id).orElse(null);
	    /*if (grievance.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Grievance with id %d not found", id));
	    }*/
	    
	    return grievance;
	}

	public void save(Optional<Grievance> grievance) {
		// TODO Auto-generated method stub
		grievanceRepository.save(grievance);
	}

	/*//Delete a vehicle by id
	public void delete(int id) {
		vehicleRepository.deleteById(id);
	}*/
}
