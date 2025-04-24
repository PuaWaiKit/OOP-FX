package com.inventorymanager.source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.groupfx.JavaFXApp.*;

import javafx.scene.control.cell.PropertyValueFactory;

public class InventoryM_Stocks extends InventoryM implements viewData{
	
	private String posID;
	private String itemsID;
	private String posStatus;
	private int posQuantity;
	private double posPrice;
	
	private String itemStockID;
	private String itemStockName;
	private int itemStock;
	
	public InventoryM_Stocks() {
		
	}
	
	public InventoryM_Stocks(String posID, String itemsID, int posQuantity, double posPrice,String posStatus) {
		
		this.posID = posID;
		this.itemsID = itemsID;
		this.posStatus = posStatus;
		this.posQuantity = posQuantity;
		this.posPrice = posPrice;
	}
	
	public InventoryM_Stocks(String itemStockID, String itemStockName, int itemStock) {
	
		this.itemStockID = itemStockID;
		this.itemStockName	= itemStockName;
		this.itemStock = itemStock;
		
	}
	
    //For Purchsae Order
	public String getPosID() { return posID; }
	
	public String getItemsID() { return itemsID; }
	
	public String getPosStatus() { return posStatus; }
	
	public int getPosQuantity() { return posQuantity; }
	
	public double getPosPrice() { return posPrice; }
	
	
	// For Stock
	public String getItemStockID() { return itemStockID; }
	
	public String getItemStockName() { return itemStockName; }
	
	public int getItemStock() { return itemStock; }
	
	@Override
	public StringBuilder ReadTextFile() throws IOException
	{	
		//InputStream stream= getClass().getClassLoader().getResourceAsStream("Data/ItemsList.txt");
		BufferedReader reader= new BufferedReader(new FileReader("Data/PurchaseOrder.txt"));
		builder= new StringBuilder();
		String line;
		
		while ((line=reader.readLine())!=null) 
		{
			String[] data=line.split(",");
			builder.append(data[0]).append(","); 
			builder.append(data[1]).append(","); 
			builder.append(data[2]).append(","); 
			builder.append(data[3]).append(",");
			builder.append(data[4]).append(","); 
			builder.append(data[5]).append(",");
			builder.append(data[6]).append(",");
			builder.append(data[7]).append("\n");
			
		}
		return builder;
		
	}
	
	public StringBuilder ReadStockTextFile() throws IOException
	{	
		//InputStream stream= getClass().getClassLoader().getResourceAsStream("Data/ItemsList.txt");
		BufferedReader reader= new BufferedReader(new FileReader("Data/ItemsList.txt"));
		builder= new StringBuilder();
		String line;
		
		while ((line=reader.readLine())!=null) 
		{
			String[] data=line.split(",");
			builder.append(data[0]).append(","); 
			builder.append(data[1]).append(","); 
			builder.append(data[2]).append(","); 
			builder.append(data[3]).append(",");
			builder.append(data[4]).append("\n"); 
			
		}
		return builder;
		
	}
}
