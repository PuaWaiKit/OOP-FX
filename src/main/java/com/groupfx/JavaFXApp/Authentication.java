package com.groupfx.JavaFXApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Authentication { 
	//private static final String FileLocate= "";
	private Map<String,String> UserMap= new HashMap<>();
	private Map<String,String> roleMap= new HashMap<>();
	
	public Authentication() 
	{
		
	}
	
//	public static void main(String[] args) 
//	{
//		Authentication auth= new Authentication();
//		auth.LoadData();
//		System.out.print(auth.TestCase());
//	}
	
	
	public void LoadData() {
		 InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Data/UserData.txt");//maven and gardle use inputstram
		
		try(BufferedReader BRead= new BufferedReader(new InputStreamReader(inputStream)))
		{
			String line="";
			while ((line=BRead.readLine())!=null) 
			{
				String [] UserParts= line.split(","); //0= UserName 1= UserPassword 2=UserRoles
				if(UserParts.length==3) 
				{
					UserMap.put(UserParts[0], UserParts[1]); //name and passwords
					roleMap.put(UserParts[0], UserParts[2]); // name and roles
				}
			}
			
			
			
		}
		catch(IOException e) 
		{
			
		}
		
		
	}
	
	public boolean UserAuth(String UserName, String Password) 
	{
		if(UserMap.containsKey(UserName.trim()) && UserMap.get(UserName.trim()).equals(Password.trim())) 
		{
			return true;
		}
		return false;
		
	}
	
//	public String TestCase() 
//	{
//		Authentication auth= new Authentication();
//		auth.LoadData();
//		String t="";
//		for (String key: UserMap.keySet()) 
//		{
//			t=UserMap.get(key);
//		}
//		return t;
//	}
	
}
