package com.techlander.corporationapp.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Grievance {
	
	@jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private String firstname;
	private String lastname;
	private String address1;
	private String address2;
	private String email;
	private String taluk;
	private String city;
	private String pcode;
	private String country;
	private String incidentlocation;
	private String complaintdetails;
	private String desiredoutcome;
	private String signature;
	private String status;
	private String resolutiondetails;
	private String resolvedby;
	private String complaintImage;
	private String resolvedImage;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date complaintdate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date incidentdate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date resolutiondate;
}
