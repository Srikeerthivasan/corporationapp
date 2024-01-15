package com.techlander.corporationapp.security.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techlander.corporationapp.security.models.Role;

//import com.kindsonthegenius.fleetapp.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	@Query(
	        value = "SELECT * FROM role WHERE id NOT IN (SELECT role_id FROM user_role WHERE user_id = ?1)", 
	        nativeQuery = true
	)
	Set<Role> getUserNotRoles(Integer userId);
}
