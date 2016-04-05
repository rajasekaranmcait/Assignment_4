package com.example.bean;
public class ProductDetail 
{
	//declare the varialbles for properties
	private long id;
	private String firstname;
	private String lastname;
	private String productname;
	private double price;
	private int quantity;
	private double total;
	//use get and set functions for value set and print
	public long getId()
	{
	  return id;
	}
	public void setId(long id)
	{
	  this.id = id;
	}
	public String getFirstname()
	{
		return firstname;
	}
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	public String getLastname()
	{
		return lastname;
	}
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	public String getProductname()
	{
		return productname;
	}
	public void setProductname(String productname)
	{
		this.productname = productname;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	public double getTotal()
	{
		return total;
	}
	public void setTotal(double total) 
	{
		this.total = total;
	}
	//override using toSting() function
	@Override
	public String toString() 
	{
		return "ProductDetail [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", productname="
				+ productname + ", price=" + price + ", quantity=" + quantity + ", total=" + total + "]";
	}
}
