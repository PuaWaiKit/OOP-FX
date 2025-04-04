package com.salesmanager.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.groupfx.JavaFXApp.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalesM_Items extends SalesM implements viewData, modifyData{
	
	private String Name;
	private String ID;
	private double UnitPrice;
	private String Supplier;
	private int Stock;
	protected StringBuilder builder;
	private ObservableList<SalesM_Items> cacheList = FXCollections.observableArrayList(); 
	private int index;
	private String resultString;
	
	public SalesM_Items() 
	{
		
		
	}
	
	public SalesM_Items(String resultString) 
	{
		
		this.resultString = resultString;
	}
	
	public SalesM_Items(int index, ObservableList<SalesM_Items> cacheList) 
	{
		
		this.index = index;
		this.cacheList = cacheList;
	}
	
	public SalesM_Items(String ID, String Name, String Supplier,int Stock,double UnitPrice) 
	{
		
	    this.ID = ID;
        this.Name = Name;
        this.Supplier = Supplier;
        this.Stock = Stock;
        this.UnitPrice = UnitPrice;
	}
	
	public SalesM_Items(String ID, String Name, String Supplier, int Stock, double UnitPrice, ObservableList<SalesM_Items> cacheList, int index) 
	{
		
	    this.ID = ID;
        this.Name = Name;
        this.Supplier = Supplier;
        this.Stock = Stock;
        this.UnitPrice = UnitPrice;
        this.cacheList = cacheList;
        this.index = index;
	}
	
	
    public String getId() { return ID; }
    
    public String getName() { return Name; }
    
    public String getSupplier() { return Supplier; }
    
    public int getStock() { return Stock; }
    
    public double getUnitPrice() { return UnitPrice; }
    
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
			builder.append(data[0]).append(","); // ID
            builder.append(data[1]).append(","); // Name
            builder.append(data[2]).append(","); // Supplier ID
            builder.append(data[3]).append(","); // Stock
            builder.append(data[4]).append("\n"); // UnitPrice
			
		}
		
		return builder;
		
	}
	
	@Override
	public void AddFunc() {
		
		cacheList.add(new SalesM_Items(		
				
				ID,
				Name,
				Supplier,
				Stock,
				UnitPrice
				
				));
	}
	
	@Override
	public void EditFunc() {
		
		cacheList.set(index, new SalesM_Items(		
				
				ID,
				Name,
				Supplier,
				Stock,
				UnitPrice
				
				));
	}
	
	@Override
	public void DeleteFunc() {
		
		cacheList.remove(index);
	}
	
	@Override
	public void SaveFunc() {
		
		String[] parts = resultString.split("\n");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data/ItemsList.txt", false))) {
			for (String part : parts) {
				
            writer.write(part);
            writer.newLine(); 
            
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public ObservableList<SalesM_Items>  getCacheList() {
		
		return cacheList;
	}
}
