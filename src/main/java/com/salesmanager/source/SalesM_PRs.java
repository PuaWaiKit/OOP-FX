package com.salesmanager.source;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.groupfx.JavaFXApp.prSource;

public class SalesM_PRs  extends SalesM implements prSource {
	
	private String Id;
	private String Item_ID;
	private int Quantity;
	private String Date;
	private String SalesM;
	private String Status;

	public String getId() { return Id; }
    public String getItem_ID() { return Item_ID; }
    public int getQuantity() { return Quantity; }
    public String getDate() { return Date; }
    public String getSalesM() { return SalesM; }
    public String getStatus() { return Status; }
    
    public SalesM_PRs() {
    	
    }
    
    public SalesM_PRs(String Id, String Item_ID, int Quantity, String Date, String SalesM, String Status) {
        this.Id = Id;
        this.Item_ID = Item_ID;
        this.Quantity = Quantity;
        this.Date = Date;
        this.SalesM = SalesM;
        this.Status = Status;
    }
    
	@Override
	public void viewPR() {
		try {
            StringBuilder data = ReadTextFile();
            System.out.println(data.toString()); 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void modifyPR() {
		
	}
	

	public StringBuilder ReadTextFile() throws IOException
	{	
		
		BufferedReader reader= new BufferedReader(new FileReader("Data/prList.txt"));
		builder= new StringBuilder();
		String line;
		
		while ((line=reader.readLine())!=null) 
		{
			String[] data=line.split(",");
			builder.append(data[0]).append(","); //ID
			builder.append(data[1]).append(","); //Name
			builder.append(data[2]).append(","); //Supplier Name
			builder.append(data[3]).append(","); //Stock
			builder.append(data[4]).append(","); //UnitPrice
			builder.append(data[5]).append("\n"); //Sales Manager
			
		}
		
		return builder;
		
	}
}