package com.bis.inventory.rest.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bis.inventory.entity.Inventory;
import com.bis.inventory.exception.DeleteFailedException;
import com.bis.inventory.exception.InsertFailedException;
import com.bis.inventory.exception.RetrieveFailedException;
import com.bis.inventory.exception.UpdateFailedException;
import com.bis.inventory.rest.dto.ResponseDTO;
import com.bis.inventory.rest.dto.StatusDTO;
import com.bis.inventory.services.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	Logger  LOG = Logger.getLogger(InventoryController.class.getName());
	
	@Autowired(required=true)
	@Qualifier("InventoryService")
	private InventoryService inventoryService;
	
	@GetMapping("/{id}")
	public Inventory getInventory(@PathVariable("id") long id){
		LOG.info("getInventory for " + id);
		try {
			return inventoryService.getInventory(id);
		} catch (RetrieveFailedException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<Inventory>> allInventory(){
		LOG.info("getallInventory");
		List<Inventory> list = inventoryService.getAllInventory();
		return new ResponseEntity<List<Inventory>>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Inventory editInventory(@RequestBody Inventory inventory){
		 try {
			 inventory = inventoryService.updateInventory(inventory);
		} catch (UpdateFailedException e) {
			
			e.printStackTrace();
		}
		return inventory;	
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseDTO addInventory(@RequestBody Inventory inventory){
		
		ResponseDTO response = new ResponseDTO();
	    try {
	    	inventory = inventoryService.addInventory(inventory);
			response.setData(inventory);
            response.setStatus(0);
            response.setMessage("SUCCESS");
            
		} catch (InsertFailedException e) {
			
			e.printStackTrace();
			response.setData(inventory);
            response.setStatus(-1);
            response.setMessage("Failed: " + e);	
		}
		return response;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public StatusDTO delete(@PathVariable("id") long id) throws RetrieveFailedException{
		LOG.info("delete Inventory for " + id);
		StatusDTO status = new StatusDTO();
		try {
			inventoryService.deleteInventory(id);
			status.setMessage("Inventory Deleted Successfully");
			status.setStatus(200);
		} catch (DeleteFailedException e) {
			e.printStackTrace();
			status.setMessage("Inventory Deleted Failed");
			status.setStatus(-200);
		}
		return status;
	}	
}