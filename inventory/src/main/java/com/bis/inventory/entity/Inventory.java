package com.bis.inventory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory implements Serializable{
	
	
	private static final long serialVersionUID = -3586462230205903340L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Column(name="ITEM_NAME")
	private String itemname;
	
	@Column(name="COST_PRICE")
	private long costprice;
	
	@Column(name="SELLING_PRICE")
	private long sellingprice;
	
	@Column(name="QUANTITY")
	private long quantity;

	public String getItemname() {
		return itemname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (costprice ^ (costprice >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((itemname == null) ? 0 : itemname.hashCode());
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
		result = prime * result + (int) (sellingprice ^ (sellingprice >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (costprice != other.costprice)
			return false;
		if (id != other.id)
			return false;
		if (itemname == null) {
			if (other.itemname != null)
				return false;
		} else if (!itemname.equals(other.itemname))
			return false;
		if (quantity != other.quantity)
			return false;
		if (sellingprice != other.sellingprice)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", itemname=" + itemname + ", costprice=" + costprice + ", sellingprice="
				+ sellingprice + ", quantity=" + quantity + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCostprice() {
		return costprice;
	}

	public void setCostprice(long costprice) {
		this.costprice = costprice;
	}

	public long getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(long sellingprice) {
		this.sellingprice = sellingprice;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

}
