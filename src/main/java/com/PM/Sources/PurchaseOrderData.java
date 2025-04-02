package com.PM.Sources;

import java.io.IOException;

public class PurchaseOrderData extends PManagerOrder{
	private String Id,name,Pm;
	private int Quantity;
	private double Price;
	
	public PurchaseOrderData() {}
	
	public PurchaseOrderData(String Id, String name, String Quantity, String Price, String Pm) 
	{
		super(Id,name,Quantity,Price,Pm);
	}
	
	
	public PurchaseOrderData(String Id, String name, int Quantity, double Price, String Pm) 
	{
		this.Id=Id;
		this.name=name;
		this.Quantity=Quantity;
		this.Price=Price;
		this.Pm=Pm;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getId() 
	{
		return Id;
	}
	
	public int getQuantity() 
	{
		return Quantity;
	}
	
	public double getPrice() 
	{
		return Price;
	}
	
	public String getPm() 
	{
		return Pm;
	}
	
	public StringBuilder ViewPo() throws IOException 
	{
		return super.ReadTextFile();
	}
}
