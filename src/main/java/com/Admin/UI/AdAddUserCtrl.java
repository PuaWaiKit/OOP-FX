package com.Admin.UI;

import java.io.IOException;

import com.Admin.Source.AdminSources;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AdAddUserCtrl {

    @FXML
    private Button DelBtn;

    @FXML
    private TableColumn<AdminSources, String> PasswordTab;

    @FXML
    private Button RefBtn;

    @FXML
    private TableColumn<AdminSources, String> RoleTab;

    @FXML
    private Button SaveBtn;

    @FXML
    private ToggleGroup SelectedRoles;

    @FXML
    private TableColumn<AdminSources, String> UserNameTab;

    @FXML
    private TableView<AdminSources> UserView;

    @FXML
    private RadioButton fmSelected;

    @FXML
    private RadioButton imSelected;

    @FXML
    private TextField password;

    @FXML
    private RadioButton pmSelected;

    @FXML
    private RadioButton smSelected;

    @FXML
    private TextField username;

    
    public void initialize() throws IOException 
    {
    	UserNameTab.setCellValueFactory(new PropertyValueFactory<>("user"));
    	PasswordTab.setCellValueFactory(new PropertyValueFactory<>("password"));
    	RoleTab.setCellValueFactory(new PropertyValueFactory<>("role"));
    	loadTable();
    }
    
    public void loadTable() throws IOException 
    {
    	AdminSources source= new AdminSources();
    	ObservableList<AdminSources> obList= FXCollections.observableArrayList();
    	String[] data= source.ReadTextFile().toString().split("\n");
    	for(String split:data) 
    	{
    		String[] spl= split.split(",");
    		obList.add(new AdminSources(
    					spl[0],
    					spl[1],
    					spl[2]
    				));
    		
    	}
    	UserView.setItems(obList);
    	
    }
    
    
    @FXML
    public void DelClick(MouseEvent event) {
    	int SelectedRow= UserView.getSelectionModel().getSelectedIndex();
    	
    }

    @FXML
    public void RefClick(MouseEvent event) {

    }

    @FXML
    public void SelectedRows(MouseEvent event) {

    }

    @FXML
    public void saveClick(MouseEvent event) {
    	RadioButton selectedRadio= (RadioButton) SelectedRoles.getSelectedToggle();
    	String role= selectedRadio.getText();
    	AdminSources source= new AdminSources(username.getText(),password.getText(),role);
    	source.AddFunc();
    	if(source.Checking()) 
    	{
    		Alert alert= new Alert(AlertType.INFORMATION);
    		alert.setTitle("Add User");
    		alert.setContentText("Add User Sucessful");
    		alert.showAndWait();
    	}
    	else 
    	{
    		Alert alert= new Alert(AlertType.ERROR);
    		alert.setTitle("Add User");
    		alert.setContentText("Add User Unsucessful");
    		alert.showAndWait();
    	}
    }

}
