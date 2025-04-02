package com.groupfx.JavaFXApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class ViewList implements viewData
{
	private String Name;
	private String ID;
	private double UnitPrice;
	private String Supplier;
	private int Stock;
	protected StringBuilder builder;
	
	public ViewList() 
	{
		
	}
	
	
	
	public ViewList(String ID, String Name, String Supplier,int Stock,double UnitPrice ) 
	{
	    this.ID = ID;
        this.Name = Name;
        this.Supplier = Supplier;
        this.Stock = Stock;
        this.UnitPrice = UnitPrice;
	}
	
	
    public String getId() 
    { 
    	return ID; 
    }
    
    public String getName() 
    { 
    	return Name; 
    }
    
    public String getSupplier() 
    { 
    	return Supplier; 
    }
    
    public int getStock() 
    { 
    	return Stock; 
    }
    
    public double getUnitPrice() 
    { 
    	return UnitPrice; 
    }
	
    @Override
    public StringBuilder ReadTextFile() throws IOException
	{	
		//InputStream stream= getClass().getClassLoader().getResourceAsStream("Data/ItemsList.txt");
		BufferedReader reader= new BufferedReader(new FileReader("Data/ItemsList.txt"));
		builder= new StringBuilder();
		String line;
		
		while ((line=reader.readLine())!=null) 
		{
			String[] data=line.split(",");
			builder.append(data[0]).append(","); //ID
			builder.append(data[1]).append(","); //Name
			builder.append(data[2]).append(","); //Supplier Name
			builder.append(data[3]).append(","); //Stock
			builder.append(data[4]).append("\n"); //UnitPrice
			
		}
		return builder;
		
	}
}
