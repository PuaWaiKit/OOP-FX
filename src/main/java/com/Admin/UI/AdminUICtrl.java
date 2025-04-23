package com.Admin.UI;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminUICtrl implements Initializable {

    @FXML
    private Button AddUser;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private AnchorPane drawerPane;

    @FXML
    private Button homeBtn;

    @FXML
    private AnchorPane nvgPane;
    
    private Stage stage;
    private Scene scene;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadNewContent("/fxml/adDashboard.fxml");
        
    }
	
	
	public void loadNewContent(String fxmlFile) {
		
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), contentPane);
        fadeOut.setFromValue(1.0);
       	fadeOut.setToValue(0.0); 
       	fadeOut.setCycleCount(1);

       	fadeOut.setOnFinished(event -> {
    	 try {
        	     AnchorPane newContent = FXMLLoader.load(getClass().getResource(fxmlFile));
        	     contentPane.getChildren().setAll(newContent);

        	      AnchorPane.setTopAnchor(newContent, 0.0);
        	      AnchorPane.setBottomAnchor(newContent, 0.0);
        	      AnchorPane.setLeftAnchor(newContent, 0.0);
        	      AnchorPane.setRightAnchor(newContent, 0.0);

        	      FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), contentPane);
        	      fadeIn.setFromValue(0.0);
        	      fadeIn.setToValue(1.0);
        	      fadeIn.setCycleCount(1);
        	      fadeIn.play();
        	      
        	} catch (IOException e) {
        	        e.printStackTrace();
        	}
        });
        fadeOut.play();
	}
	
	
	public boolean SwitchAlert() throws IOException
	{
		Alert alert= new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Switch Pages?");
    	alert.setContentText("All data haven't save will lost");
    	alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO); //add button yes and no
    	Optional<ButtonType> result= alert.showAndWait(); // wait until user select
    	alert.setHeaderText("Do You Want To Switch Pages?");
    	
    	if(result.isPresent() && result.get()==ButtonType.YES) 
    	{
    		 FileWriter writer = new FileWriter("Data/Cache.txt");
    		 writer.close();
    		 return true;
    	}
    	return false;
	}
    

    @FXML
    public void LogoutB(MouseEvent event) throws IOException {
    	Alert alert= new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("LogOut?");
    	alert.setContentText("All data haven't save will lost");
    	alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO); //add button yes and no
    	Optional<ButtonType> result= alert.showAndWait(); // wait until user select
    	alert.setHeaderText("Do You Want To Log Out ?");
    	
    	if(result.isPresent() && result.get()== ButtonType.YES) 
    	{
    		
    		FileWriter writer= new FileWriter("Data/Cache.txt");
    		writer.close();
    		
			Parent root= FXMLLoader.load(getClass().getResource("/fxml/Sample.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			scene= new Scene(root);
			stage.setScene(scene);
			stage.show();
    	}
    }

    @FXML
    public void handleChangeHome(ActionEvent event) throws IOException {
    	if(SwitchAlert()) 
    	{
    		loadNewContent("/fxml/adDashboard.fxml");
    	}
    }

    @FXML
    public void handleChangeUser(ActionEvent event) throws IOException {
     	if(SwitchAlert()) 
    	{
    		loadNewContent("/fxml/adNewUser.fxml");
    	}
    }

    private boolean drawerOpen=false;
    
    @FXML
   public void toggleDrawer(ActionEvent event) {
       	TranslateTransition transition = new TranslateTransition(Duration.millis(300), drawerPane);

        if (drawerOpen) {
            transition.setToY(+300);
        } else {
            transition.setToY(0);
        }

        transition.play();
        drawerOpen = !drawerOpen;
    }

}
