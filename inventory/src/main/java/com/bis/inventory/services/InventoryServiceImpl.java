package com.bis.inventory.services;

import java.util.List;

import javax.persistence.RollbackException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bis.inventory.dao.InventoryDAO;
import com.bis.inventory.entity.Inventory;
import com.bis.inventory.exception.DeleteFailedException;
import com.bis.inventory.exception.InsertFailedException;
import com.bis.inventory.exception.RetrieveFailedException;
import com.bis.inventory.exception.UpdateFailedException;

@Service("inventoryService")
@Transactional
public class InventoryServiceImpl implements InventoryService {

	Logger  LOG = Logger.getLogger(InventoryServiceImpl.class.getName());
	@Autowired(required=true)
	@Qualifier("inventoryDAO")
	private InventoryDAO inventoryDAO;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public Inventory addInventory(Inventory inventory) throws  InsertFailedException{
				LOG.info("adding Inventory");
		System.out.println("Inventory Service create invoked:");
		try{
			inventory = inventoryDAO.addInventory(inventory);
		
		LOG.error("inventory added");
		}
		catch(RollbackException e){
			LOG.error(e);
			
		}
		return inventory;
	}
	
	@Transactional
	public Inventory updateInventory(Inventory inventory) throws UpdateFailedException{
		LOG.info("updating Inventory");
		System.out.println("Inventory Service Update invoked:");
		inventory = inventoryDAO.updateInventory(inventory);
		LOG.debug(" Inventory updated");
		return inventory;	
	}

	public Inventory getInventory(long id) throws RetrieveFailedException {
		LOG.info("getInventory for " + id);
		try
		{
			Inventory invt = inventoryDAO.getInventory(id);
			LOG.info("Received Inventory details..." + invt.getItemname());
			return invt;
		}
		catch(Exception ex)
		{
			LOG.info("Received inventory details exception..." ,ex);			
		}
		return null;
	}

	public List<Inventory> getAllInventory() {
		return inventoryDAO.getInventory();
	}
	
	@Transactional
	public boolean deleteInventory(long id) throws DeleteFailedException {
		LOG.info("Deleting Inventory with " + id);
		try {
			inventoryDAO.deleteInventory(id);
		} catch (RetrieveFailedException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
}
