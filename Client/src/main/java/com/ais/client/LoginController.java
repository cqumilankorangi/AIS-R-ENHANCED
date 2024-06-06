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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
        if (selectedUserType != null) {
            switch (selectedUserType) {
                case "Admin":
                    // Successful login, navigate to AdminPanel
                    showSuccessDialog("Admin");
                    try {
                        App.setRoot("AdminPanel");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Management":
                    // Successful login, navigate to ManagementPanel
                    showSuccessDialog("Management");
                    try {
                        App.setRoot("ManagementPanel");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Recruit":
                    // Successful login, navigate to RecruitsPanel
                    showSuccessDialog("Recruit");
                    try {
                        App.setRoot("RecruitsPanel");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        } else {
            // Failed login, show error dialog
            showErrorDialog("Login Error", "Please select a user type.");
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

    //Login sucess pop up
    private void showSuccessDialog(String userType) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Login Successful");
        alert.setHeaderText(null);
        alert.setContentText("Welcome, " + userType + "!");
        alert.showAndWait();
    }

      //Login error pop up
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
