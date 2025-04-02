package com.PM.Sources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import com.groupfx.JavaFXApp.*;

public class PManagerOrder implements viewData, modifyData {
	private String Id,name,Pm;
	private int Quantity;
	private double Price;
	private InputStream stream;
	private String Filepath="Data/PurchaseOrder.txt";
	private boolean Checking=false;
	
	
	public PManagerOrder() 
	{
		
	}
	
	public PManagerOrder(String Id, String name, int Quantity, double Price, String Pm ) 
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
	
	
	public boolean checkingFunc() 
	{
		return Checking;
	}
	
	@Override
	public void AddFunc() 
	{
		StringBuilder builder= new StringBuilder();
	
		
		
		try(BufferedWriter writer= new BufferedWriter(new FileWriter(Filepath,true)))
		{
			//String Data= MessageFormat.format("{0},{1}.{2},{3},{4}",Id,name,Quantity,Price,Pm);
			//writer.write(Data);
			
			builder.append(Id).append(",");
			builder.append(name).append(",");
			builder.append(Quantity).append(",");
			builder.append(Price).append(",");
			builder.append(Pm).append("\n");
			writer.write(builder.toString());
			//writer.newLine();
			Checking=true;
		}
		catch(IOException e) 
		{	Checking=false;
			e.printStackTrace();
		}
	}
	
	@Override
	public void DeleteFunc() 
	{
		
	}
	
	@Override
	public void SaveFunc() 
	{
		
	}
	
	@Override
	public void EditFunc() 
	{
		
	}
	
	@Override
	public StringBuilder ReadTextFile() throws IOException
	{
		StringBuilder builder= new StringBuilder();
		
		BufferedReader reader= new BufferedReader(new FileReader(Filepath));
		
		String line;
		while ((line=reader.readLine())!=null) 
		{
			if(line.trim().isEmpty()) continue; //Skip Empty Space/Data
			
			String[] data=line.split(",");
			builder.append(data[0]).append(",");
			builder.append(data[1]).append(",");
			builder.append(data[2]).append(",");
			builder.append(data[3]).append(",");
			builder.append(data[4]).append("\n");
		}
		reader.close();
		return builder;
		
	}
}
