package com.salesmanager.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.groupfx.JavaFXApp.*;

public class SalesM {

	private String userID;
	
	protected StringBuilder builder;
	
	public SalesM() {
		
	}
	
	public SalesM(String userID) {
		this.userID = userID;
	}
	
	public String getId() 
    { 
    	return userID; 
    }
	
}


class smPRs extends SalesM implements prSource {

	@Override
	public void viewPR() {
		try {
            StringBuilder data = ReadTextFile(); // 调用 ReadTextFile() 方法
            System.out.println(data.toString()); // 打印文件内容（或其他操作）
        } catch (IOException e) {
            e.printStackTrace(); // 处理异常
        }
	}

	@Override
	public void modifyPR() {
		
	}
	

	public StringBuilder ReadTextFile() throws IOException
	{	
		String userID = super.getId();
		InputStream stream= getClass().getClassLoader().getResourceAsStream("Data/prList.txt");
		BufferedReader reader= new BufferedReader(new InputStreamReader(stream));
		builder= new StringBuilder();
		String line;
		
		while ((line=reader.readLine())!=null) 
		{
			String[] data=line.split(",");
			builder.append(data[0]).append(","); //ID
			builder.append(data[1]).append(","); //Name
			builder.append(data[2]).append(","); //Supplier Name
			builder.append(data[3]).append(","); //Stock
			builder.append(data[4]).append(","); //UnitPrice
			builder.append(data[5]).append("\n"); //Author
			
		}
		return builder;
		
	}
	
}
