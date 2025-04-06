package com.salesmanager.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.groupfx.JavaFXApp.modifyData;
import com.groupfx.JavaFXApp.viewData;

import javafx.collections.ObservableList;

public class SalesM_DailyS extends SalesM implements viewData, modifyData {
	
	private String Id;
	private String itemId;
	private String itemName;
	private int totalSales;
	private final String author = getUserId();
	private String tempAuthor;
	
	//Variable for the modification
	private int selectedIndex;
	private String resultString;
	private ObservableList<SalesM_DailyS> cachelist;
	
	public SalesM_DailyS() {
		
	}
	
	public SalesM_DailyS(String resultString) {
		
		this.resultString = resultString;
	}
	
	public SalesM_DailyS(int selectedIndex, ObservableList<SalesM_DailyS> cacheList) {
		
		this.selectedIndex = selectedIndex;
		this.cachelist = cacheList;
	}
	
	public SalesM_DailyS(String Id, String itemId, String itemName, int totalSales, String author) {
        this.Id = Id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.totalSales = totalSales;
        this.tempAuthor = author;
    }
	
	public SalesM_DailyS(String Id, String itemId, String itemName, int totalSales, String author, ObservableList<SalesM_DailyS> cacheList, int Index) {
		this.Id = Id;
        this.itemId = itemId;
        this.itemName = itemName;
        this.totalSales = totalSales;
        this.tempAuthor = author;
        this.cachelist = cacheList;
        this.selectedIndex = Index;
    }
	
	public String getId() { return Id; }
    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public int getTotalSales() { return totalSales; }
    public String getAuthor() { return tempAuthor; }
	
    @Override
	public StringBuilder ReadTextFile() throws IOException
	{	
		//InputStream stream= getClass().getClassLoader().getResourceAsStream("Data/ItemsList.txt");
		BufferedReader reader= new BufferedReader(new FileReader("Data/dailySales.txt"));
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
		
		cachelist.add(new SalesM_DailyS(		
				
				Id,
				itemId,
				itemName,
				totalSales,
				author
				
				));
		
	}
	
	@Override
	public void EditFunc() {
		
		cachelist.set(selectedIndex, new SalesM_DailyS(		
				
				Id,
				itemId,
				itemName,
				totalSales,
				author
				
				));
		
	}
	
	@Override
	public void DeleteFunc() {
		
		cachelist.remove(selectedIndex);
	}
	
	@Override
	public void SaveFunc() {
		
		String[] parts = resultString.split("\n");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data/dailySales.txt", false))) {
			for (String part : parts) {
				
            writer.write(part);
            writer.newLine(); 
            
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public ObservableList<SalesM_DailyS>  getCacheList() {
		
		return cachelist;
	}
}
