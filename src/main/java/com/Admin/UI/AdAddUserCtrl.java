package com.Admin.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class AdAddUserCtrl {

    @FXML
    private Button DelBtn;

    @FXML
    private TableColumn<?, ?> PasswordTab;

    @FXML
    private Button RefBtn;

    @FXML
    private TableColumn<?, ?> RoleTab;

    @FXML
    private Button SaveBtn;

    @FXML
    private ToggleGroup SelectedRoles;

    @FXML
    private TableColumn<?, ?> UserNameTab;

    @FXML
    private TableView<?> UserView;

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

    @FXML
    void DelClick(MouseEvent event) {

    }

    @FXML
    void RefClick(MouseEvent event) {

    }

    @FXML
    void SelectedRows(MouseEvent event) {

    }

    @FXML
    void saveClick(MouseEvent event) {

    }

}
