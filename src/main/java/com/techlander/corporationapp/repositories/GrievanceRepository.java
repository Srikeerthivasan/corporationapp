package com.techlander.corporationapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlander.corporationapp.models.Grievance;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Integer> {

	void save(Optional<Grievance> grievance);

}
