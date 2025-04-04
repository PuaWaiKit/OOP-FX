package com.PM.Sources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.groupfx.JavaFXApp.viewData;

public class PMViewReq  implements viewData{
	private String Prid;
	private String ItemsId;
	private int Quantity;
	private String date;
	private String SalesM;
	private String status;
	private String suppId;
	
	public PMViewReq() {}
	
	public PMViewReq(String Prid, String ItemsId, int Qty, String date, String SalesM ,String suppId,String status) 
	{
		this.Prid=Prid;
		this.ItemsId=ItemsId;
		this.Quantity=Qty;
		this.date=date;
		this.SalesM=SalesM;
		this.suppId=suppId;
		this.status=status;
	}
	
	
	public String getPrId() 
	{
		return Prid;
	}
	
	public String getItemsid() 
	{
		return ItemsId;
	}
	
	public int getQty() 
	{
		return Quantity;
	}
	
	public String getDate() 
	{
		return date;
	}
	
	public String getSalesM() 
	{
		return SalesM;
	}
	
	public String getSupp() 
	{
		return suppId;
	}
	
	
	public String getStatus() 
	{
		return status;
	}
	
	@Override
	public StringBuilder ReadTextFile() throws IOException
	{
		StringBuilder builder= new StringBuilder();
		String line;
		try(BufferedReader reader= new BufferedReader(new FileReader("Data/prList.txt")))
		{
			while((line=reader.readLine())!=null) 
			{
				String[] data= line.split(",");
				builder.append(data[0]).append(",");
				builder.append(data[1]).append(",");
				builder.append(data[2]).append(",");
				builder.append(data[3]).append(",");
				builder.append(data[4]).append(",");
				builder.append(data[5]).append(",");
				builder.append(data[6]).append("\n");
			}
		}
		return builder;
	}
	
	
}
