package com.financemanager.source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.PM.Sources.PManagerOrder;

public class FMAppPO extends PManagerOrder{
	
	private String PoId;
	private String ItemsId;
	private int Qty;
	private double cost;
	private String PM;
	private String Status;
	private int ClickCount;
	private int SelectedLine;
	private String newdata;
	private String supplier;
	private boolean check;
	
	
	public FMAppPO() {}
	
	public FMAppPO(int SelectedLine, String data,String supplier) 
	{	
		this.newdata=data;
		this.SelectedLine=SelectedLine;
		this.supplier=supplier;
		
	}
	
	public FMAppPO(String PoId, String ItemsId, int Qty, double cost, String PM,String Status) 
	{	
		super(PoId,ItemsId,Qty,cost,PM,Status);
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
	
	
	@Override
	public boolean checkingFunc() 
	{
		return check;
	}
	
	@Override
	public StringBuilder ReadTextFile() throws IOException
	{
		return super.ReadTextFile();
	}
	
	
	public ArrayList<String> GetSupplier() throws IOException
	{
		String line;
		ArrayList<String> RSupplier=new ArrayList<>();
		BufferedReader reader= new BufferedReader(new FileReader("Data/Suppliers.txt"));
		while((line=reader.readLine())!=null) 
		{
			String[] data= line.split(",");
			RSupplier.add(data[0]);
		}
		reader.close();
		return RSupplier;
	}
	
	
	
	public StringBuffer GetPoId(int selectedIndex) throws IOException
	{
		String line;
		int LineCount=0;
		StringBuffer buffer= new StringBuffer();
		BufferedReader reader= new BufferedReader(new FileReader("Data/PurchaseOrder.txt"));
		while((line=reader.readLine())!=null) 
		{	
			String[] data=line.split(",");
			if(LineCount==selectedIndex) 
			{
				buffer.append(data[5]).append(","); //status
				buffer.append(data[6]).append("\n"); //Supplier
				reader.close();
				break;
			}
			LineCount++;
		}
		return buffer;
	}
	
	
	
	/**
	 * Problem Occur Please Mind *
	 */
	@Override
	public void EditFunc() 
	{
		String line;
		StringBuffer buffer= new StringBuffer();
		
		if(ClickCount>0) {
		
		
			try(BufferedReader reader= new BufferedReader(new FileReader("Data/PurchaseOrder.txt")))
			{
				while((line=reader.readLine())!=null) 
				{
					String[] data=line.split(",");
					buffer.append(data[0]).append(",");
					buffer.append(data[1]).append(",");
					buffer.append(data[2]).append(",");
					buffer.append(data[3]).append(",");
					buffer.append(data[4]).append(",");
					buffer.append(data[5]).append(",");
					buffer.append(data[6]).append("\n");
					
				}
				
				FileWriter writer= new FileWriter("Data/Cache.txt");
				writer.write(buffer.toString());
				writer.close();
				
			}
			catch(IOException e) 
			{
				e.printStackTrace();
			}	
		}
		
		try 
		{
			List<String> CacheData= new ArrayList<>(Files.readAllLines(Paths.get("Data/Cache.txt")));
			String[] index= GetPoId(SelectedLine).toString().split(",");
			
			if(index[0].equals("Pending")) 
			{
				if(SelectedLine>=0 || SelectedLine<=CacheData.size()) 
				{  
					String NewData= String.join(",", newdata,supplier);
					CacheData.set(SelectedLine, NewData);
				}
				
				Files.write(Paths.get("Data/Cache.txt"), CacheData, StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
				check=true;
				System.out.println("Adding Sucess"+ super.checkingFunc()+"Checking"+check);
			}else 
			{	
				check=false;
				System.out.println("Adding Not"+"Checking"+check);
			}
			
		}catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void SaveFunc() 
	{	

		
		
	}
}
