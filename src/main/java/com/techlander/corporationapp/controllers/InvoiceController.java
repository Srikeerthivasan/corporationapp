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

import com.techlander.corporationapp.models.Client;
import com.techlander.corporationapp.models.Invoice;
import com.techlander.corporationapp.models.InvoiceStatus;
import com.techlander.corporationapp.services.ClientService;
import com.techlander.corporationapp.services.InvoiceService;
import com.techlander.corporationapp.services.InvoiceStatusService;

@Controller
public class InvoiceController {

	@Autowired private InvoiceService invoiceService;
	@Autowired private ClientService clientService;
	@Autowired private InvoiceStatusService invoiceStatusService;
	
	
	@GetMapping("/invoices")
	public String getInvoices(Model model) {
		List<Invoice> invoiceList = invoiceService.getInvoices();
		model.addAttribute("invoices", invoiceList);
		
		List<Client> clientList = clientService.getClients();
		model.addAttribute("clients", clientList);
		
		List<InvoiceStatus> invoiceStatusList = invoiceStatusService.getInvoiceStatuss();
		model.addAttribute("invoicestatuses", invoiceStatusList);
		
		return "invoice";
	}
	
	@PostMapping("/invoices/addNew")
	public String addNew(Invoice invoice) {
		invoiceService.save(invoice);
		return "redirect:/invoices";
	}
	
	
	
	@GetMapping("/invoices/findById/{id}")
    @ResponseBody
	public Optional<Invoice> findById(@PathVariable("id") int id) {
		Optional<Invoice> invoice = invoiceService.findById(id);
	    if (invoice.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invoice with id %d not found", id));
	    }

	    return invoice;
	}
	
	@RequestMapping(value="/invoices/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Invoice invoice) {
		invoiceService.save(invoice);
		return "redirect:/invoices";
	}
	
	@RequestMapping(value="/invoices/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		invoiceService.delete(id);
		return "redirect:/invoices";
	}
}
