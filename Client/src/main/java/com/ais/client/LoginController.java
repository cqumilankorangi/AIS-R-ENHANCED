/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ais.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author 12213436
 */
public class LoginController implements Initializable {

    @FXML
    private Button RegisterButton;

    @FXML
    private ComboBox<String> userType;

    @FXML
    void loginHandler(ActionEvent event) throws IOException {
        String selectedUserType = userType.getValue();
        if (null != selectedUserType) switch (selectedUserType) {
            case "Admin":
                try {
                    App.setRoot("AdminPanel");
                } catch (IOException e) {
                    e.printStackTrace();
                }   break;
            case "Management":
                try {
                    App.setRoot("ManagementPanel");
                } catch (IOException e) {
                    e.printStackTrace();
                }   break;
            case "Recruit":
                try {
                    App.setRoot("RecruitsPanel");
                } catch (IOException e) {
                    e.printStackTrace();
                }   break;
            default:
                break;
        }
    }

    @FXML
    void registerHandler(ActionEvent event) throws IOException {
        App.setRoot("Register");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] qualifications = {"Admin", "Management", "Recruit"};

        // Set the items to the ComboBox
        userType.setItems(FXCollections.observableArrayList(qualifications));
    }

}
