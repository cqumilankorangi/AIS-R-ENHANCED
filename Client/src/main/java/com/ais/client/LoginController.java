/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ais.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author 12213436
 */
public class LoginController implements Initializable {

    @FXML
    private Button RegisterButton;

    @FXML
    void loginHandler(ActionEvent event) throws IOException {
        App.setRoot("AdminPanel");
    }

    @FXML
    void registerHandler(ActionEvent event) throws IOException {
        App.setRoot("Register");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
