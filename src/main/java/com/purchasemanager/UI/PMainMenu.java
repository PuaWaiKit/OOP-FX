package com.purchasemanager.UI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class PMainMenu {

    @FXML
    private Label DateLbl;

    @FXML
    private PieChart ReqChart;

    @FXML
    private Label ReqNum;

    @FXML
    private Label RoleLbl;

    @FXML
    private Label WelcomeLbl;

    
   
    
    public void initialize() throws IOException 
    {
    	String SetName="Welcome, "+SetText();
    	WelcomeLbl.setText(SetName);
    	
    }
    
    
    
    public String SetText() throws IOException 
    {
    	BufferedReader reader= new BufferedReader(new FileReader("Data/Log.txt"));
    	String Name=reader.readLine();
    	reader.close();
    	return Name;
    	
    }
    
    
    
}
