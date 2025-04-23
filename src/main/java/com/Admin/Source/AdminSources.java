package com.Admin.Source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.groupfx.JavaFXApp.modifyData;
import com.groupfx.JavaFXApp.viewData;

public class AdminSources implements viewData,modifyData {

	private String User,password,role;
	private boolean checking=false;
	private int SelectedIndex;
	
	public AdminSources() {}
	
	
	public AdminSources(String User, String password, String role) 
	{
		this.User=User;
		this.password=password;
		this.role=role;
	}
	
	public AdminSources(int SelectedIndex) 
	{
		this.SelectedIndex=SelectedIndex;
	}
	
	public String getUser() { return User;}
	public String getPassword() {return password;}
	public String getRole() {return role;}
	
	
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
	
	@Override
	public StringBuilder ReadTextFile() throws IOException
	{
		String line;
		StringBuilder builder= new StringBuilder();
		BufferedReader reader= new BufferedReader(new FileReader("Data/UserData.txt"));
		
		while ((line=reader.readLine())!=null) 
		{
			String[] data= line.split(",");
			builder.append(data[0]).append(",");
			builder.append(data[1]).append(",");
			builder.append(data[2]).append("\n");
		}
		reader.close();
		
		return builder;
	}
	
	
	public boolean Checking() 
	{
		return checking;
	}
	
	
	@Override
	public void AddFunc() 
	{
		try(BufferedWriter writer= new BufferedWriter(new FileWriter("Data/UserData.txt",true)))
		{
			writer.newLine();
			writer.write(User+","+password+","+role);
			checking=true;
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			checking=false;
		}
	}
	
	@Override
	public void EditFunc() {}
	
	@Override
	public void DeleteFunc() 
	{
		String line;
		int index=0;
		try(BufferedReader reader= new BufferedReader(new FileReader("Data/UserData.txt")))
		{
			while((line=reader.readLine())!=null) 
			{
				if(index==SelectedIndex) 
				{
					
				}
				index++;
			}
			
			
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void SaveFunc() 
	{
		
	}
	
	
}
