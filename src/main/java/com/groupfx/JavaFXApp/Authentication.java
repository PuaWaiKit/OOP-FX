package com.groupfx.JavaFXApp;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


enum UserRole
{
	admin("ad"),
	salesManager("sm"),
	purchaseManager("pm"),
	inventoryManager("im"),
	financeManager("fm");
	
	private final String descrip;
	
	UserRole(String descrip){
		this.descrip=descrip;
	}
	
	public String getDescription() 
	{
		return descrip;
	}
	
}




public class Authentication { 
	//private static final String FileLocate= "";
	private Map<String,String> UserMap= new HashMap<>();
	private Map<String,String> roleMap= new HashMap<>();
	private String CurrRole;
	private Stage stage;
	private Scene scene;
	private String Name;
	
	
	
	public Authentication() 
	{
		
	}
	
	public String getName() 
	{
		return Name;
	}
//	public static void main(String[] args) 
//	{
//		Authentication auth= new Authentication();
//		auth.LoadData();
//		System.out.print(auth.TestCase());
//	}
	
	
	public void LoadData() {
		
		
		try(BufferedReader BRead= new BufferedReader(new FileReader("Data/UserData.txt")))
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
	
	public boolean UserAuth(String UserName, String Password) throws IOException 
	{
		if(UserMap.containsKey(UserName.trim()) && UserMap.get(UserName.trim()).equals(Password.trim())) 
		{	
			CurrRole=roleMap.getOrDefault(UserName, "Undentified");
			BufferedWriter writer = new BufferedWriter(new FileWriter("Data/Log.txt"));
			writer.write(UserName);
			writer.newLine();
			writer.close();
			return true;
		}
		return false;
		
	}
	
	public String getRoles() 
	{
		return CurrRole;
	}
	
	
	
	public void SwitchScene(MouseEvent event) throws IOException
	{			
	/**
	 * String[] data= {"sm","pm","im","ad","fm"};
	 * Please Mind that all the role interface/main menu must start with sm/pmInterface...
	 * And the Text File Data must same as enum values 
	 * 
	 * */
		for(UserRole rol: UserRole.values()) 
		{
			if (CurrRole.equals(rol.toString())) 
			{	String filePath= MessageFormat.format("/fxml/{0}Interface.fxml",rol.getDescription());  //getDescription is enum method
				Parent root= FXMLLoader.load(getClass().getResource(filePath));
				stage=(Stage)((Node)event.getSource()).getScene().getWindow();
				scene= new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			
		}
		
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
