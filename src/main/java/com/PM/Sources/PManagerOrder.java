package com.PM.Sources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.groupfx.JavaFXApp.*;

public class PManagerOrder implements viewData, modifyData {
	private String Id,name,Pm;
	private int Quantity;
	private double Price;
	private InputStream stream;
	private String Filepath="Data/PurchaseOrder.txt";
	private boolean Checking=false;
	private int LineNum;
	private String newData;
	private StringBuilder builder= new StringBuilder();
	private int lineCount;

	
	public PManagerOrder() 
	{
		
	}
	
	public PManagerOrder(int LineNum, String newData) 
	{
		this.LineNum= LineNum;
		this.newData= newData;
	}
	
	public PManagerOrder(int LineNum) 
	{
		this.LineNum=LineNum;
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
	
	public int LineCount() 
	{
		return lineCount;
	}
	
	
	public boolean checkingFunc() 
	{
		return Checking;
	}
	
	@Override
	public void AddFunc() 
	{
//		try(BufferedReader Read= new BufferedReader(new FileReader("Data/PurchaseOrder.txt"))) 
//		{
//			String line;
//			while((line=Read.readLine())!=null) 
//			{
//				String[] dataOld= line.split(",");
//				builder.append(dataOld[0]).append(",");
//				builder.append(dataOld[1]).append(",");
//				builder.append(dataOld[2]).append(",");
//				builder.append(dataOld[3]).append(",");
//				builder.append(dataOld[4]).append("\n");
//			}
//		}
//		catch(IOException e) 
//		{
//			e.printStackTrace();
//		}
		try(BufferedWriter writer= new BufferedWriter(new FileWriter("Data/Cache.txt",true)))
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
	
			try(BufferedReader ReadSec= new BufferedReader(new FileReader("Data/Cache.txt")))
			{ 	String line;
				try 
				{
					while((line=ReadSec.readLine())!=null) 
					{
						String[] dataNew=line.split(",");
						builder.append(dataNew[0]).append(",");
						builder.append(dataNew[1]).append(",");
						builder.append(dataNew[2]).append(",");
						builder.append(dataNew[3]).append(",");
						builder.append(dataNew[4]).append("\n");
					}
				} 
				catch (IOException e) 
				{
							
					e.printStackTrace();
				}
				
			 
			} 
			catch (FileNotFoundException e2) 
			{
			
				e2.printStackTrace();
			} 
			catch (IOException e2) 
			{
				
				e2.printStackTrace();
			}
			
			
			
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data/Cache.txt"))) 
			{
				writer.write(builder.toString());
				
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
			
			try {
				List<String> lineR= new ArrayList<>(Files.readAllLines(Paths.get("Data/Cache.txt"))); //get file into Array
				if(LineNum>=0 &&LineNum<lineR.size()) //size one based start from 1 
				{
					lineR.remove(LineNum); //remove it
				}
				
				//Write into file WRITE is to write in TRUNCATEEXISTING is to clear all the data in file and write new 
				Files.write(Paths.get("Data/Cache.txt"), lineR, StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
				Checking=true;
			} catch (IOException e) {
				Checking=false;
				e.printStackTrace();
			}
			
			
	}
		
	
	
	@Override
	public void SaveFunc() 
	{
		try(BufferedReader reader= new BufferedReader(new FileReader("Data/Cache.txt")))
		{
			try(BufferedWriter writer= new BufferedWriter(new FileWriter(Filepath)))
			{
				//String Data= MessageFormat.format("{0},{1}.{2},{3},{4}",Id,name,Quantity,Price,Pm);
				//writer.write(Data);
				String line;
				while ((line=reader.readLine())!=null) 
				{
					String[] dataSet= line.split(",");
					builder.append(dataSet[0]).append(",");
					builder.append(dataSet[1]).append(",");
					builder.append(dataSet[2]).append(",");
					builder.append(dataSet[3]).append(",");
					builder.append(dataSet[4]).append("\n");
					lineCount++;
				}
				writer.write(builder.toString());
				builder.setLength(0);
				//writer.newLine();
				Checking=true;
				BufferedWriter Delete= new BufferedWriter(new FileWriter("Data/Cache.txt"));
			}
			catch(IOException e) 
			{	Checking=false;
				e.printStackTrace();
			}
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@Override
	public void EditFunc() 
	{
		try
		{
			List<String> EditList= new ArrayList<>(Files.readAllLines(Paths.get(Filepath)));
			if(LineNum>=0 && LineNum<=EditList.size()) 
			{
				EditList.set(LineNum,newData);
				
			}
			Files.write(Paths.get(Filepath),EditList,StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
			Checking=true;
		}
		catch(IOException e) 
		{
			e.printStackTrace();
			Checking=false;
		}
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
		
		
		try (BufferedWriter ReadCache = new BufferedWriter(new FileWriter("Data/Cache.txt"))) {
			ReadCache.write(builder.toString());
		}
		reader.close();
		return builder;
		
	}
}
