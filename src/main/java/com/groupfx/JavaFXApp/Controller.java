
package com.groupfx.JavaFXApp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class Controller {
	
    @FXML
    private ImageView flipImage;
    @FXML
    private Label Forgotlbl;

    @FXML
    private Button LoginBtn;
    
    @FXML
    private TextField Pwbx;

    @FXML
    private TextField UsernameBx;
    
    @FXML
    private PasswordField passwordbox;

    @FXML
    private ImageView pwChangePic;

    private boolean isFirstImg=true;



   
    @FXML
    public void initialize() {
    	Rotate rotateY = new Rotate(0, 100, 100, 0, Rotate.Y_AXIS);
        flipImage.getTransforms().add(rotateY);

        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0), new KeyValue(rotateY.angleProperty(), 0)),
            new KeyFrame(Duration.seconds(5), new KeyValue(rotateY.angleProperty(), 360)) 
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
      
    }
    

    @FXML
    public void forgotClick(MouseEvent event) {
    	
    }

    @FXML
    public void loginAct(MouseEvent event) {
    	Authentication auth= new Authentication();
		auth.LoadData();
		if(auth.UserAuth(UsernameBx.getText(),passwordbox.getText())) 
		{
			Alert alert= new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucessfull");
			alert.setHeaderText(null);
			alert.setContentText("Sucess Login");
			alert.showAndWait();
		
		}
		else 
		{
			Alert alert= new Alert(AlertType.WARNING);
			alert.setTitle("Wraning");
			alert.setContentText("Username or Password Incorrect! ");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
    }
    
    
    

    @FXML
    public void pwClick(MouseEvent event) {
    	pwChangePic.setImage(new Image(getClass().getResource("/img/hide.png").toExternalForm()));
    	
    	if(isFirstImg)
    	{
    		pwChangePic.setImage(new Image(getClass().getResource("/img/hide.png").toExternalForm()));
    		Pwbx.setText(passwordbox.getText());
    		passwordbox.setVisible(false);
    		Pwbx.setVisible(true);
    	}
    	else
    	{
    		pwChangePic.setImage(new Image(getClass().getResource("/img/view.png").toExternalForm()));
    		passwordbox.setText(Pwbx.getText());
    		passwordbox.setVisible(true);
    		Pwbx.setVisible(false);
    	}
    	isFirstImg=!isFirstImg;  //set the current status
    	
    }
    

}
