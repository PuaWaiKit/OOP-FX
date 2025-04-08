package com.salesmanager.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.groupfx.JavaFXApp.*;

import javafx.collections.ObservableList;

public class SalesM_PRs  extends SalesM implements viewData, modifyData {
	
	private String Id;
	private String Item_ID;
	private int Quantity;
	private String Date;
	private String SalesM;
	private String Status;

	//Variable for the modification
	private int selectedIndex;
	private String resultString;
	private ObservableList<SalesM_PRs> cacheList;
		
	public String getId() { return Id; }
    public String getItem_ID() { return Item_ID; }
    public int getQuantity() { return Quantity; }
    public String getDate() { return Date; }
    public String getSalesM() { return SalesM; }
    public String getStatus() { return Status; }
    
    public SalesM_PRs() {
    	
    }
    
    public SalesM_PRs(String resultString) {
    	
    	this.resultString = resultString;
    }
    
    public SalesM_PRs(int selectedIndex, ObservableList<SalesM_PRs> cacheList) {
    	
    	this.cacheList = cacheList;
        this.selectedIndex = selectedIndex;
    }
    
    public SalesM_PRs(String Id, String Item_ID, int Quantity, String Date, String SalesM, String Status) {
        this.Id = Id;
        this.Item_ID = Item_ID;
        this.Quantity = Quantity;
        this.Date = Date;
        this.SalesM = SalesM;
        this.Status = Status;
    }
    
    public SalesM_PRs(String Id, String Item_ID, int Quantity, String Date, String SalesM, String Status, ObservableList<SalesM_PRs> cacheList,int selectedIndex) {
        this.Id = Id;
        this.Item_ID = Item_ID;
        this.Quantity = Quantity;
        this.Date = Date;
        this.SalesM = SalesM;
        this.Status = Status;
        this.cacheList = cacheList;
        this.selectedIndex = selectedIndex;
        
    }
    
	@Override
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
			builder.append(data[3]).append(","); //Stoc.k
			builder.append(data[4]).append(","); //UnitPrice
			builder.append(data[5]).append("\n"); //Sales Manager
			
		}
		
		return builder;
		
	}
	
	@Override
	public void AddFunc() {
		
		cacheList.add(new SalesM_PRs(		
				
				Id,
				Item_ID,
				Quantity,
				Date,
				SalesM,
				Status
				
				));
		
	}
	
	@Override
	public void EditFunc() {
		
		cacheList.set(selectedIndex, new SalesM_PRs(		
				
				Id,
				Item_ID,
				Quantity,
				Date,
				SalesM,
				Status
				
				));
		
	}
	
	@Override
	public void DeleteFunc() {
		
		cacheList.remove(selectedIndex);
	}
	
	@Override
	public void SaveFunc() {
		
		String[] parts = resultString.split("\n");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data/Suppliers.txt", false))) {
			for (String part : parts) {
				
            writer.write(part);
            writer.newLine(); 
            
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public ObservableList<SalesM_PRs>  getCacheList() {
		
		return cacheList;
	}
}