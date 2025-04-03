package com.salesmanager.source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.groupfx.JavaFXApp.modifyData;
import com.groupfx.JavaFXApp.viewData;

public class SalesM_Suppliers  extends SalesM implements viewData, modifyData{
	
	private String Id;
	private String Name;
	private String ContactNum;
	private String Address;
	private String Item;
	
	public SalesM_Suppliers() {
		
	}
	
	public SalesM_Suppliers(String id, String name, String contactNum, String address, String item) {
        this.Id = id;
        this.Name = name;
        this.ContactNum = contactNum;
        this.Address = address;
        this.Item = item;
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
		builder= new StringBuilder();
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
		
	}
	
	@Override
	public void EditFunc() {
		
	}
	
	@Override
	public void DeleteFunc() {
		
	}
	
	@Override
	public void SaveFunc() {
		
	}
}
