package com.salesmanager.source;

public class SalesM {

	private String userID;
	
	protected StringBuilder builder;
	
	public SalesM() { 
		
	}
	
	public SalesM(String userID) { 
		
		this.userID = userID;
	}
	
	public SalesM(String userID, String type) {
		
		this.userID = userID;
		
	}
	
	public String getUserId() { 
		
		return userID;
		}
	
}
	

