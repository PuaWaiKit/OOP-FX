package com.financemanager.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.groupfx.JavaFXApp.Purchase_Order;



public class FMPayment extends Purchase_Order {
	
	private String Status;
	public FMPayment() {}
	//PO006,I0005,1111111,10.9,Ming,Approve,S001

	public FMPayment(String Id, String name, int Quantity, double Price,String Status,String Supplier) 
	{
		super(Id, name,Quantity,Price,Supplier,Status);
		this.Status=Status;
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
			builder.append(data[5]).append(",");// Status
			builder.append(data[6]).append("\n"); //Supplier
		}
		
		
		try (BufferedWriter ReadCache = new BufferedWriter(new FileWriter("Data/Cache.txt"))) {
			ReadCache.write(builder.toString());
		}
		reader.close();
		return builder;
		
	}
	
	
	@Override
	public String getStatus() 
	{
		return Status;
	}
	
	public StringBuffer LoadData() throws IOException
	{	
		String line;
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader= new BufferedReader(new FileReader(Filepath));
		while((line=reader.readLine())!=null) 
		{	String[] data= line.split(",");
			
			if(data[5].equals("Approve")) 
			{
				buffer.append(data[0]).append(",");
				buffer.append(data[1]).append(",");
				buffer.append(data[2]).append(",");
				buffer.append(data[3]).append(",");
				buffer.append(data[5]).append(",");// Status
				buffer.append(data[6]).append("\n");
			}else continue;
		}
		reader.close();
		return buffer;
	}
	
	
	
	
	
	
}
