package com.Admin.Source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdminSources {

	
	
	public AdminSources() {}
	
	
	public String RetName() throws IOException
	{	
		
		String line;
		String name=null;
		BufferedReader reader= new BufferedReader(new FileReader("Data/Log.txt"));
		
		while((line=reader.readLine())!=null) 
		{
			String[] data= line.split(",");
			name=data[0];
			
		}
		reader.close();
		return name;
	}
}
