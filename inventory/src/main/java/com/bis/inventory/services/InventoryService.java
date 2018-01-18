package com.bis.inventory.services;

import java.util.List;

import com.bis.inventory.entity.Inventory;
import com.bis.inventory.exception.DeleteFailedException;
import com.bis.inventory.exception.InsertFailedException;
import com.bis.inventory.exception.RetrieveFailedException;
import com.bis.inventory.exception.UpdateFailedException;

public interface InventoryService {

	public Inventory addInventory(Inventory inventory) throws InsertFailedException;
	public Inventory updateInventory(Inventory inventory) throws UpdateFailedException;
	public boolean deleteInventory(long id) throws DeleteFailedException, RetrieveFailedException;
	public Inventory getInventory(long id) throws RetrieveFailedException;
	public List<Inventory> getAllInventory();
}
