package com.salesmanager.source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.groupfx.JavaFXApp.modifyData;
import com.groupfx.JavaFXApp.viewData;

public class SalesM_Suppliers  extends SalesM implements viewData, modifyData{
	
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
			builder.append(data[0]).append(","); //ID
			builder.append(data[1]).append(","); //Name
			builder.append(data[2]).append(","); //Supplier Name
			builder.append(data[3]).append(","); //Stock
			builder.append(data[4]).append("\n"); //UnitPrice
			
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
