package com.purchasemanager.UI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class PMainMenu {

    @FXML
    private Label DateLbl;

    @FXML
    private Label TimeLbl;
    
    @FXML
    private PieChart ReqChart;

    @FXML
    private Label ReqNum;

    @FXML
    private Label RoleLbl;

    @FXML
    private Label WelcomeLbl;

    private String[] data;
   
    @FXML
    public void initialize() throws IOException 
    {
    	String SetName="Welcome, "+SetText();
    	WelcomeLbl.setText(SetName);
    	RoleLbl.setText(data[1].toUpperCase());
    	
    	DateTimeFormatter format= DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	DateTimeFormatter TimeFormat= DateTimeFormatter.ofPattern("HH:mm:ss");
    	Timeline clock= new Timeline(new KeyFrame(Duration.seconds(1), event->
    	{
    		LocalDate Date= LocalDate.now();
    		LocalTime Time= LocalTime.now();
    		DateLbl.setText(Date.format(format));
    		TimeLbl.setText(Time.format(TimeFormat));
    		
    	}));
    	clock.setCycleCount(Timeline.INDEFINITE);
    	clock.play();
    			
    	
    }
    
    
    
    public String SetText() throws IOException 
    {
    	BufferedReader reader= new BufferedReader(new FileReader("Data/Log.txt"));
    	String line;
    	while((line=reader.readLine())!=null) 
    	{
    		data=line.split(",");
    	}
    	String Name=data[0];
    	reader.close();
    	return Name;
    	
    }
    
    
    
}
