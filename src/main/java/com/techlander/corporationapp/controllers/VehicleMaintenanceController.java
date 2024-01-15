package com.techlander.corporationapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.techlander.corporationapp.models.Supplier;
import com.techlander.corporationapp.models.Vehicle;
import com.techlander.corporationapp.models.VehicleMaintenance;
import com.techlander.corporationapp.services.SupplierService;
import com.techlander.corporationapp.services.VehicleMaintenanceService;
import com.techlander.corporationapp.services.VehicleService;

@Controller
public class VehicleMaintenanceController {

	@Autowired private VehicleMaintenanceService vehicleMaintenance;
	@Autowired private VehicleService vehicleService;
	@Autowired private SupplierService supplierService;
	
	@GetMapping("/vehiclemaintenances")
	public String getVehicleMaintenances(Model model) {
		List<VehicleMaintenance> vehiclemaintenanceList = vehicleMaintenance.getVehicleMaintenances();
		model.addAttribute("vehiclemaintenances", vehiclemaintenanceList);
		
		List<Vehicle> vehicleList = vehicleService.getVehicles();
		model.addAttribute("vehicles", vehicleList);
		
		List<Supplier> supplierList = supplierService.getSuppliers();
		model.addAttribute("suppliers", supplierList);
		
		return "vehiclemaintenance";
	}
	
	@PostMapping("/vehiclemaintenances/addNew")
	public String addNew(VehicleMaintenance vehiclemaintenance) {
		vehicleMaintenance.save(vehiclemaintenance);
		return "redirect:/vehiclemaintenances";
	}
	
	
	
	@GetMapping("/vehiclemaintenances/findById/{id}")
    @ResponseBody
	public Optional<VehicleMaintenance> findById(@PathVariable("id") int id) {
		Optional<VehicleMaintenance> vehiclemaintenance = vehicleMaintenance.findById(id);
	    if (vehiclemaintenance.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleMaintenance with id %d not found", id));
	    }

	    return vehiclemaintenance;
	}
	
	@RequestMapping(value="/vehiclemaintenances/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleMaintenance vehiclemaintenance) {
		vehicleMaintenance.save(vehiclemaintenance);
		return "redirect:/vehiclemaintenances";
	}
	
	@RequestMapping(value="/vehiclemaintenances/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		vehicleMaintenance.delete(id);
		return "redirect:/vehiclemaintenances";
	}
}
