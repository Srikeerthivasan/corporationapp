package com.techlander.corporationapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlander.corporationapp.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
