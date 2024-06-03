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
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author 12213436
 */
public class ManagementRegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<String> position;

    @FXML
    void saveHandler(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] positions = {"Full-time", "Part-time", "Volunteer"};

        // Set the items to the ComboBox
        position.setItems(FXCollections.observableArrayList(positions));
    }

}
