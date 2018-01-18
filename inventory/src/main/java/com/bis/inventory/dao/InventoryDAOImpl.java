package com.bis.inventory.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bis.inventory.dao.InventoryDAO;
import com.bis.inventory.dao.InventoryDAOImpl;
import com.bis.inventory.entity.Inventory;
import com.bis.inventory.exception.DeleteFailedException;
import com.bis.inventory.exception.InsertFailedException;
import com.bis.inventory.exception.RetrieveFailedException;
import com.bis.inventory.exception.UpdateFailedException;

@Repository("inventoryDAO")
@Transactional

public class InventoryDAOImpl implements InventoryDAO{
	
	Logger  LOG = Logger.getLogger(InventoryDAOImpl.class.getName());
	@PersistenceContext
	public EntityManager entityManager;

	@Transactional(readOnly=false)
	public Inventory addInventory(Inventory inventory) throws InsertFailedException
	{
		LOG.info("Adding Inventory"); 
		try{
			entityManager.persist(inventory);
		}catch(RollbackException e){
			
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));			
			LOG.error("Exception" +stack );
			throw new InsertFailedException("Failed to insert Inventory"+ e);
		}
		finally{
		}
		return inventory;		
	}

	@Transactional(readOnly=false)
	public Inventory updateInventory(Inventory inventory) throws UpdateFailedException 
	{
		LOG.info("updating Inventory");
		try{
			entityManager.merge(inventory);
		}catch(Exception e){
			LOG.error("Exception" +e);
			throw new UpdateFailedException("Failed to Update Inventory"+ e);
		}
		finally{
		}
		return inventory;
	}

	@Transactional(readOnly=false)
	public void deleteInventory(long id) throws DeleteFailedException, RetrieveFailedException
	{
		LOG.info("Deleting Inventory");
		Inventory customer;
		customer = getInventory(id);
		try{
			entityManager.remove(customer);
		}catch(Exception e){
			LOG.error("Exception" +e);
			throw new DeleteFailedException("Failed to Delete Inventory"+ e);		
		}
		LOG.info("Inventory Deleted");
	}

	@Transactional(readOnly=true)
	public Inventory getInventory(long id) throws RetrieveFailedException
	{
		String sql = "select inventory from Inventory inventory where inventory.id="+id;
		try{
			return (Inventory) entityManager.createQuery(sql).getSingleResult();
		}catch(Exception e){
			LOG.error(e.getStackTrace().toString());
			LOG.error("Exception", e);
			throw new RetrieveFailedException("Failed to Retrieve Inventory"+ e);
		}
	}

	@Transactional(readOnly=true)
	public List<Inventory> getInventory() {
		LOG.info("Getting Inventory");
		return entityManager.createQuery("select m from Inventory m").getResultList();
	}
	
}