package com.salesmanager.UI;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;


public class smUICtrl implements Initializable {
	
	@FXML
	private AnchorPane contentPane;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
        loadNewContent("/fxml/smItems.fxml");
    }
	
	@FXML
    private void handleChangeItems() {
        loadNewContent("/fxml/smItems.fxml");
    }
	
	@FXML
	private void handleChangeSuppliers() {
		loadNewContent("/fxml/smSuppliers.fxml");
	}
	
	@FXML
	private void handleChangeDailyS() {
		loadNewContent("/fxml/smDailySales.fxml");
	}
	
	@FXML
	private void handleChangePO() {
		loadNewContent("/fxml/smPO.fxml");
	}
	
	@FXML
	private void handleChangePR() {
		loadNewContent("/fxml/smPR.fxml");
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
	
//  << Lambda Explanation>>
//	fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
//	    @Override
//	    public void handle(ActionEvent event) {
//	        try {
//
//	            AnchorPane newContent = FXMLLoader.load(getClass().getResource(fxmlFile));
//	            contentPane.getChildren().setAll(newContent);
//
//	            AnchorPane.setTopAnchor(newContent, 0.0);
//	            AnchorPane.setBottomAnchor(newContent, 0.0);
//	            AnchorPane.setLeftAnchor(newContent, 0.0);
//	            AnchorPane.setRightAnchor(newContent, 0.0);
//
//	            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), contentPane);
//	            fadeIn.setFromValue(0.0);
//	            fadeIn.setToValue(1.0);
//	            fadeIn.setCycleCount(1);
//	            fadeIn.play();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	});

	@FXML
    private AnchorPane drawerPane;

    private boolean drawerOpen = false;

    @FXML
    private void toggleDrawer() {
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
