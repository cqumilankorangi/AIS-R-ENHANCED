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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 12213436
 */
public class AdminPanelController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Pane AddRecruitsPane;

    @FXML
    private Pane UpdateRecruitsPane;

    @FXML
    private Pane myprofilePane;

    @FXML
    private Button otp;

    @FXML
    private Pane otpPane;

    @FXML
    private Button profile;

    @FXML
    private Button add_recruit;

    @FXML
    private Button update_recruit;

    @FXML
    void handleNav(ActionEvent event) {
        if (event.getSource() == add_recruit) {
            AddRecruitsPane.toFront();
        } else if (event.getSource() == update_recruit) {
            UpdateRecruitsPane.toFront();
        } else if (event.getSource() == otp) {
            otpPane.toFront();
        } else if (event.getSource()
                == profile) {
            myprofilePane.toFront();
        }
    }

    @FXML
    void updateAccountHandler(ActionEvent event) {

    }

    @FXML
    void saveRecruitHandler(ActionEvent event) {

    }

    @FXML
    void updateRecruitHandler(ActionEvent event) {

    }

    @FXML
    void searchRecruitHandler(ActionEvent event) {

    }

    @FXML
    void logoutHandler(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
