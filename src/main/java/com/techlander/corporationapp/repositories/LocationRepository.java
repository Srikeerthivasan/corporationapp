package com.techlander.corporationapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlander.corporationapp.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
