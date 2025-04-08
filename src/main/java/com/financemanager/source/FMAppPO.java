package com.financemanager.source;

public class FMAppPO {
	
	private String PoId;
	private String ItemsId;
	private int Qty;
	private double cost;
	private String PM;
	private String Status;
	
	public FMAppPO() {}
	
	public FMAppPO(String PoId, String ItemsId, int Qty, double cost, String PM,String Status) 
	{
		this.PoId=PoId;
		this.ItemsId=ItemsId;
		this.Qty=Qty;
		this.cost=cost;
		this.PM=PM;
		this.Status=Status;
		
	}
	
	public String getPoId() 
	{
		return PoId;
	}
	
	public String getItemsId() 
	{
		return ItemsId;
	}
	
	public int getQty() 
	{
		return Qty;
	}
	
	public double getCost() 
	{
		return cost;
	}
	
	public String getPm() 
	{
		return PM;
	}
	
	public String getStatus() 
	{
		return Status;
	}
}
