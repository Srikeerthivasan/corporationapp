package com.techlander.corporationapp.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.techlander.corporationapp.models.Grievance;
import com.techlander.corporationapp.models.User;
import com.techlander.corporationapp.models.UserPrincipal;
import com.techlander.corporationapp.models.VehicleType;
import com.techlander.corporationapp.security.models.Role;
import com.techlander.corporationapp.security.services.RoleService;
import com.techlander.corporationapp.services.GrievanceService;
import com.techlander.corporationapp.services.UserService;

@Controller
public class GrievanceController {
	
	@Autowired private GrievanceService grievanceService;
	@Autowired private RoleService roleService;
	@Autowired private UserService userService;
	
	//image directory creation
	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
	
	//Controller to redirect to Grievance logging form
	@GetMapping("grievanceforms")
	public String getGrievanceform(Model model) {
		return "grievanceform";
	}
	
	//Controller to redirect to Grievance list page
	@GetMapping("grievancelists")
	public String getGrievancelist(Model model) {
		List<Grievance> grievancesList = grievanceService.getGrievances();
		model.addAttribute("grievances", grievancesList);
		System.out.println("Directory: " + UPLOAD_DIRECTORY);
		return "grievancelist";
	}
	
	//Modified method to register a new grievance
	@PostMapping("/grievanceforms/addNew")
	public String addNew(Grievance grievance, @RequestParam("fileToUpload") MultipartFile file) 
			throws IOException{
		//Defaulting status as pending
		grievance.setStatus("Pending");
		
		//Uploading grievance image
		if (file != null) {
			StringBuilder fileNames = new StringBuilder();
		    Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		    fileNames.append(file.getOriginalFilename());
		    Files.write(fileNameAndPath, file.getBytes());
		    grievance.setComplaintImage(file.getOriginalFilename());
		    //model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
		}
		
		//Now Save
		grievanceService.save(grievance);
		return "redirect:/grievanceforms";
	}
	
	//Returning Grievance by ID
	@GetMapping("/grievancelists/findById/{id}")
    @ResponseBody
	public Grievance findById(@PathVariable("id") int id) {
		Grievance grievance = grievanceService.findById(id);
	    /*if (grievance.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Grievance with id %d not found", id));
	    }*/

	    return grievance;
	}
	
	//Updating a grievance
	@RequestMapping(value="/grievancelists/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Grievance updatedGrievance) {
		Grievance grievance = grievanceService.findById(updatedGrievance.getId());
	    // Set the updated data from updatedUser to user 
	    grievance.setIncidentdate(updatedGrievance.getIncidentdate()); // set other updated field like this
	    grievance.setIncidentlocation(updatedGrievance.getIncidentlocation());
	    grievance.setComplaintdetails(updatedGrievance.getComplaintdetails());
	    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	    grievance.setResolutiondate(date);
	    grievance.setResolutiondetails(updatedGrievance.getResolutiondetails());
	    grievance.setResolvedby(updatedGrievance.getResolvedby());
	    
	    //Updating status
	    if (grievance.getResolvedby() != null) grievance.setStatus("Solved");
	    
	    // Now save
	    grievanceService.save(grievance);
		return "redirect:/grievancelists";
	}
	
	//Checking role of the user
    @RequestMapping(value="/grievancelists/checkRole", method = {RequestMethod.PUT, RequestMethod.GET})
	public boolean checkRole(Authentication authentication) {
    	    //System.out.println(authentication.getName());
			//return authentication.getName();
    	
    		String currentUser = authentication.getName();
    		User userobj = userService.findByUserName(currentUser);
    		
    		Set<Role> roles = roleService.getUserRoles(userobj);
    		Object[] rolearray = roles.toArray();
    		
    		for(int i=0; i<roles.size(); i++) {
    			if(rolearray[i] == "ADMIN") return true;
    		}
    		
    		return false;
    }
	
	
}
