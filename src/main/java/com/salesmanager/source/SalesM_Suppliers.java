package com.salesmanager.source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;

import com.groupfx.JavaFXApp.modifyData;
import com.groupfx.JavaFXApp.viewData;

import javafx.collections.ObservableList;

public class SalesM_Suppliers implements viewData, modifyData{
	
	private String Id;
	private String Name;
	private String ContactNum;
	private String Address;
	private String Item;
	
	//Variable for the modification
	private int selectedIndex;
	private String resultString;
	private ObservableList<SalesM_Suppliers> cachelist;
	
	public SalesM_Suppliers() {
		
	}
	
	public SalesM_Suppliers(String resultString) {
		
		this.resultString = resultString;
	}
	
	public SalesM_Suppliers(int selectedIndex, ObservableList<SalesM_Suppliers> cacheList) {
		
		this.selectedIndex = selectedIndex;
		this.cachelist = cacheList;
	}
	
	public SalesM_Suppliers(String id, String name, String contactNum, String address, String item) {
        this.Id = id;
        this.Name = name;
        this.ContactNum = contactNum;
        this.Address = address;
        this.Item = item;
    }
	
	public SalesM_Suppliers(String id, String name, String contactNum, String address, String item, ObservableList<SalesM_Suppliers> cacheList, int Index) {
        this.Id = id;
        this.Name = name;
        this.ContactNum = contactNum;
        this.Address = address;
        this.Item = item;
        this.cachelist = cacheList;
        this.selectedIndex = Index;
    }
	
	public String getId() { return Id; }
    public String getName() { return Name; }
    public String getContactNum() { return ContactNum; }
    public String getAddress() { return Address; }
    public String getItem() { return Item; }
	
    @Override
	public StringBuilder ReadTextFile() throws IOException
	{	
		//InputStream stream= getClass().getClassLoader().getResourceAsStream("Data/ItemsList.txt");
		BufferedReader reader= new BufferedReader(new FileReader("Data/Suppliers.txt"));
		StringBuilder builder = new StringBuilder();
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
	
	@Override
	public void AddFunc() {
		
		cachelist.add(new SalesM_Suppliers(		
				
				Id,
				Name,
				ContactNum,
				Address,
				Item
				
				));
		
	}
	
	@Override
	public void EditFunc() {
		
		cachelist.set(selectedIndex, new SalesM_Suppliers(		
				
				Id,
				Name,
				ContactNum,
				Address,
				Item
				
				));
		
	}
	
	@Override
	public void DeleteFunc() {
		
		cachelist.remove(selectedIndex);
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
	
	public ObservableList<SalesM_Suppliers>  getCacheList() {
		
		return cachelist;
	}
}
