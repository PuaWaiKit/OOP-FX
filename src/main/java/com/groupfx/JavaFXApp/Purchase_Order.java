package com.groupfx.JavaFXApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Purchase_Order implements viewData, modifyData {
	private String Id,name,Pm;
	private int Quantity;
	private double Price;
	private InputStream stream;
	protected String Filepath="Data/PurchaseOrder.txt";
	protected boolean Checking=false;
	private int LineNum;
	private String newData;
	private StringBuilder builder= new StringBuilder();
	private int lineCount;
	private int ClickCount;
	private String Status;
	private String Supplier;
	
	public Purchase_Order() 
	{
		
	}
	
	public Purchase_Order(int LineNum, String newData) 
	{
		this.LineNum= LineNum;
		this.newData= newData;
	}
	
	public Purchase_Order(int LineNum) 
	{
		this.LineNum=LineNum;
	}
	
	public Purchase_Order(String Id, String name, int Quantity, double Price, String Pm,int LineNum ) 
	{
		this.Id=Id;
		this.name=name;
		this.Quantity=Quantity;
		this.Price=Price;
		this.Pm=Pm;
		this.LineNum=LineNum;
	}
	
	public Purchase_Order(String Id, String name, int Quantity, double Price, String Pm,String Status ) //click
	{
		this.Id=Id;
		this.name=name;
		this.Quantity=Quantity;
		this.Price=Price;
		this.Pm=Pm;
		this.Status=Status;
	}
	
	public Purchase_Order(String Id, String name, int Quantity, double Price, String Pm,String Status,String Supplier ) //click
	{
		this.Id=Id;
		this.name=name;
		this.Quantity=Quantity;
		this.Price=Price;
		this.Pm=Pm;
		this.Status=Status;
		this.Supplier=Supplier;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getId() 
	{
		return Id;
	}
	
	public int getQuantity() 
	{
		return Quantity;
	}
	
	public double getPrice() 
	{
		return Price;
	}
	
	public String getPm() 
	{
		return Pm;
	}
	
	public int LineCount() 
	{
		return lineCount;
	}
	
	public String getStatus() 
	{
		return Status;
	}
	
	public String getSupplier() 
	{
		return Supplier;
	}
	
	
//	public String IDGenerator(String PurchaseId, String prefex) 
//	{
//		
//	}
//	
	
	
	
	public StringBuilder PieCData() throws IOException
	{	String line;
		try (BufferedReader reader= new BufferedReader(new FileReader("Data/prList.txt")))
		{
			while((line=reader.readLine())!=null) 
			{
				String[] Prdata= line.split(",");
				builder.append(Prdata[5]).append("\n"); //PRStatus
				
				}
				
			}
			
		return builder;
	}
	
	public StringBuffer POStatus() throws IOException
	{
		String line;
		StringBuffer buffer= new StringBuffer();
		try (BufferedReader reader= new BufferedReader(new FileReader("Data/PurchaseOrder.txt")))
		{
			while((line=reader.readLine())!=null) 
			{
				String[] PoStatus= line.split(",");
				buffer.append(PoStatus[5]).append("\n"); //POStatus
			}
			
		}
		return buffer ;
		
	}
	
	//Overloading method
	public StringBuffer POStatus(int SelectedNum ) throws IOException 
	{
		String line;
		int LineCount=0;
		StringBuffer buffer= new StringBuffer();
		try (BufferedReader reader= new BufferedReader(new FileReader("Data/PurchaseOrder.txt")))
		{
			while((line=reader.readLine())!=null) 
			{
				String[] PoStatus= line.split(",");
				if(LineCount==SelectedNum) 
				{
					buffer.append(PoStatus[5]).append("\n"); //POStatus
					break;
				}
				LineCount++;
			}
			
		}
		return buffer ;
	}
	
	public StringBuilder RetrivePR() throws IOException
	{	String line;
	
		try (BufferedReader reader= new BufferedReader(new FileReader("Data/prList.txt")))
		{
			while((line=reader.readLine())!=null) 
			{
				String[] Prdata= line.split(",");
				builder.append(Prdata[0]).append("\n"); //PRID
			}
			
		}
		return builder;
	}
	
	
	/**
	 * Get Items Code/PrID/Qty/Status/Date from PRList TXT *
	 */
	public StringBuffer RetriveItemsID(int SelectionNum) throws IOException
	{	String line;
	
		int lineNum=0;
		StringBuffer builders= new StringBuffer();
		try (BufferedReader reader= new BufferedReader(new FileReader("Data/prList.txt")))
		{
			while((line=reader.readLine())!=null) 
			{
				String[] Prdata= line.split(",");
				if(lineNum==SelectionNum) 
				{
					builders.append(Prdata[0]).append(","); 
					builders.append(Prdata[1]).append(","); //items code
					builders.append(Prdata[2]).append(","); //qty
					builders.append(Prdata[3]).append(",");
					builders.append(Prdata[5]); //Status
					break;
				}
				lineNum++;
			}
			
		}
		return builders;
	}
	
	/**
	 * Get Items ID from TXT *
	 */
	public StringBuilder RetriveItems(String PRCode, String ItemsCode) throws IOException
	{	String line;
		StringBuilder Newbuild= new StringBuilder();
		try(BufferedReader reader= new BufferedReader(new FileReader("Data/ItemsList.txt")))
		{
			while((line=reader.readLine())!=null) 
			{
				String[] ItemsName= line.split(",");
				if(ItemsCode.equals(ItemsName[0])) 
				{
//					
					Newbuild.append(ItemsName[0]); //name
//					
					break;
				}
				else 
				{
					continue;
				}
			}
		}
		return Newbuild;
	}
	
	
	
	
	public boolean checkingFunc() 
	{
		return Checking;
	}
	
	
	/**
	 * *
	 * Checking Cache is Empty or Not
	 * (NULL Return True, else return false)
	 * @return Boolean
	 * @throws IOException
	 */
	
	public boolean CacheChecking() throws IOException
	{
		BufferedReader reader= new BufferedReader(new FileReader("Data/Cache.txt"));
		boolean check;
		if(check=reader.readLine()==null) 
		{	
			reader.close();
			return true;
		}
		reader.close();
		return false;
		
	}
	
	/**
	 * Add data to Cache.txt </br>
	 * Default Settings:</br>
	 * <ul>
	 * <li>Status Pending</li>
	 * <li>Supplier Supplier</li>
	 * <li>Payment Unpaid</li>
	 * </ul>
	 * */
	
	@Override
	public void AddFunc() 
	{
		try(BufferedWriter writer= new BufferedWriter(new FileWriter("Data/Cache.txt",true)))
		{
			//String Data= MessageFormat.format("{0},{1}.{2},{3},{4}",Id,name,Quantity,Price,Pm);
			//writer.write(Data);
			String[] Status= RetriveItemsID(LineNum).toString().split(",");
			if(!CacheChecking() && Status[4].equals("Pending")) 
			{
				builder.append(Id).append(",");
				builder.append(name).append(",");
				builder.append(Quantity).append(",");
				builder.append(Price).append(",");
				builder.append(Pm).append(",");
				builder.append("Pending").append(",");
				builder.append("Supplier").append(",");
				
				writer.write(builder.toString());
				//writer.newLine();
				Checking=true;
				ClickCount++;
			}
			else {Checking=false;}
			
		}
		catch(IOException e) 
		{	Checking=false;
			e.printStackTrace();
		}
	}
	

	
	
	
	@Override
	public void DeleteFunc() 
	{
	
			try(BufferedReader ReadSec= new BufferedReader(new FileReader("Data/Cache.txt")))
			{ 	String line;
				try 
				{
					while((line=ReadSec.readLine())!=null) 
					{
						String[] dataNew=line.split(",");
						builder.append(dataNew[0]).append(",");
						builder.append(dataNew[1]).append(",");
						builder.append(dataNew[2]).append(",");
						builder.append(dataNew[3]).append(",");
						builder.append(dataNew[4]).append(",");
						builder.append(dataNew[5]).append(",");
						builder.append(dataNew[6]).append("\n");
						
					}
				} 
				catch (IOException e) 
				{
							
					e.printStackTrace();
				}
				
			 
			} 
			catch (FileNotFoundException e2) 
			{
			
				e2.printStackTrace();
			} 
			catch (IOException e2) 
			{
				
				e2.printStackTrace();
			}
			
			
			
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data/Cache.txt"))) 
			{
				writer.write(builder.toString());
				
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
			
			try {
				String[] Status= POStatus(LineNum).toString().split("\n");
				if(!CacheChecking() && Status[0].equals("Pending")) 
				{
					List<String> lineR= new ArrayList<>(Files.readAllLines(Paths.get("Data/Cache.txt"))); //get file into Array
					if(LineNum>=0 &&LineNum<lineR.size()) //size one based start from 1 
					{
						lineR.remove(LineNum); //remove it
					}
					
					//Write into file WRITE is to write in TRUNCATEEXISTING is to clear all the data in file and write new 
					Files.write(Paths.get("Data/Cache.txt"), lineR, StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
					Checking=true;
					ClickCount++;
				}else 
				{
					Checking=false;
				}
			} catch (IOException e) {
				Checking=false;
				e.printStackTrace();
			}
			
			
	}
		
	
	
	/**
	 * Perform Saving While User Click *
	 */
	
	@Override
	public void SaveFunc() 
	{
		try(BufferedReader reader= new BufferedReader(new FileReader("Data/Cache.txt")))
		{
			
				StringBuilder NewData= new StringBuilder();
				reader.mark(1000); //set the reader length
				boolean isEmptyOrNot=reader.readLine()==null;
				reader.reset(); //reset the reader to first row
				if(!isEmptyOrNot) 
				{
					try(BufferedWriter writer= new BufferedWriter(new FileWriter(Filepath)))
					{
							//String Data= MessageFormat.format("{0},{1}.{2},{3},{4}",Id,name,Quantity,Price,Pm);
							//writer.write(Data);
							String line;
							
							while ((line=reader.readLine())!=null) 
							{	
								if(line.trim().isBlank()) {continue;}
								String[] dataSet= line.split(",");
								NewData.append(dataSet[0]).append(",");
								NewData.append(dataSet[1]).append(",");
								NewData.append(dataSet[2]).append(",");
								NewData.append(dataSet[3]).append(",");
								NewData.append(dataSet[4]).append(",");
								NewData.append(dataSet[5]).append(",");
								NewData.append(dataSet[6]).append("\n");
								
								lineCount++;
							}
							writer.write(NewData.toString());
							
							//writer.newLine();
							String[] PRID= RetriveItemsID(LineNum).toString().split(","); //get the target PRID
							if(LineNum!=-1 && PRID[4].equals("Pending")) //for Change Status on PR
							{	
								
								
								try 
								{	
										List<String> AllPr= new ArrayList<>(Files.readAllLines(Paths.get("Data/prList.txt"))); //read an save file into List
										StringBuffer buffer= new StringBuffer();
										
										for(String lines:AllPr) 
										{
											String[] Part=lines.split(","); //split the data with ,//s* means space,empty and commas
											//System.out.println(Part.length);
											if(Part[0].equals(PRID[0])) 	//check the target id is correct or not
											{
												Part[5]="Approved";
												String newl= String.join(",", Part); //build the data with commas
												buffer.append(newl).append("\n");
											}
											else 
											{
												buffer.append(lines).append("\n"); //build the data with normal data(no changes)
											}
										}
										
										Files.write(Paths.get("Data/prList.txt"), buffer.toString().getBytes(), StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
										//write in the file ,clear the existing data in the file and rewrite it, use NIO method need transfer to byte
									
								}
								
								
								
								catch(IOException e) 
								{	
									Checking=false;
									e.printStackTrace();
								}
									
									
							}
								
								
							
							}
							Checking=true;
							BufferedWriter Delete= new BufferedWriter(new FileWriter("Data/Cache.txt"));
							
					}else {Checking=false;}
						
				
				
			}
			catch(IOException e) 
			{
				e.printStackTrace();
			}
		}
	
	
			
		
		
	
	
	
	@Override
	public void EditFunc() 
	{	
		String line;
		if(ClickCount>0) 
		{
		
			try(BufferedReader readOri= new BufferedReader(new FileReader("Data/PurchaseOrder.txt"))) //initialize first
			{
				
				while((line=readOri.readLine())!=null) 
				{
					String[] oldData= line.split(",");
					builder.append(oldData[0]).append(",");
					builder.append(oldData[1]).append(",");
					builder.append(oldData[2]).append(",");
					builder.append(oldData[3]).append(",");
					builder.append(oldData[4]).append(",");
					builder.append(oldData[5]).append(",");
					builder.append(oldData[6]).append(",");
				}
			 }
			catch(IOException e) 
			{
				e.printStackTrace();
				Checking=false;
			}
		 }
		
			
			try(BufferedReader reader= new BufferedReader(new FileReader("Data/Cache.txt")))
			{
				
					while((line=reader.readLine())!=null) 
					{	
						if(line.trim().isEmpty())continue;
						
						String[] data=line.split(",");
						builder.append(data[0]).append(",");
						builder.append(data[1]).append(",");
						builder.append(data[2]).append(",");
						builder.append(data[3]).append(",");
						builder.append(data[4]).append(",");
						builder.append(data[5]).append(",");
						builder.append(data[6]).append("\n");
					}
					
					try(FileWriter writer= new FileWriter("Data/Cache.txt"))
					{
						writer.write(builder.toString());
					}
			
			}
			catch(IOException e) 
			{
				e.printStackTrace();
			}
			
			try
			{ 	
				String[] Status= POStatus(LineNum).toString().split("\n");
				if(!CacheChecking() && Status[0].equals("Pending")) 
				{
					List<String> EditList= new ArrayList<>(Files.readAllLines(Paths.get("Data/Cache.txt")));
					if(LineNum>=0 && LineNum<=EditList.size()) 
					{
						EditList.set(LineNum,newData);
						
					}
					Files.write(Paths.get("Data/Cache.txt"),EditList,StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
					Checking=true;
					
				}
				else 
				{
					Checking=false;
					
				}
			}
			catch(IOException e) 
			{
				e.printStackTrace();
				Checking=false;
				
			}
		}
		
	
	
	/**
	 * Read all Text File While Needs, abstract methods
	 * @return StringBuilder 
	 * @exception IOException
	 * */
	
	@Override
	public abstract StringBuilder ReadTextFile() throws IOException;
	
}
