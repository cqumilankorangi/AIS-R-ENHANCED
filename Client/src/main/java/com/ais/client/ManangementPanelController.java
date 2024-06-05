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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 12213436
 */
public class ManangementPanelController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Pane DashboardPane;

    @FXML
    private Pane ManagementPane;

    @FXML
    private Pane ReportPane;

    @FXML
    private Pane AddRecruitsPane;

    @FXML
    private Pane UpdateRecruitsPane;

    @FXML
    private Button dashboard;

    @FXML
    private Button management;

    @FXML
    private Button add_recruits;

    @FXML
    private Button update_recruits;

    @FXML
    private Button report;

    @FXML
    private ComboBox<String> sort;

    @FXML
    void handleNav(ActionEvent event) {
        if (event.getSource() == dashboard) {
            DashboardPane.toFront();
        } else if (event.getSource() == management) {
            ManagementPane.toFront();
        } else if (event.getSource() == report) {
            ReportPane.toFront();
        } else if (event.getSource() == add_recruits) {
            AddRecruitsPane.toFront();
        } else if (event.getSource() == update_recruits) {
            UpdateRecruitsPane.toFront();
        }
    }

    @FXML
    void handleSort(ActionEvent event) {

    }

    @FXML
    void saveRecruitHandler(ActionEvent event) {

    }

    @FXML
    void searchRecruitHandler(ActionEvent event) {

    }

    @FXML
    void updateRecruitHandler(ActionEvent event) {

    }

    @FXML
    void logoutHandler(ActionEvent event) throws IOException {
        App.setRoot("Login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] sortings = {"Lastname Dec and Groupby Locaton", "Highest level of Qualification"};

        // Set the items to the ComboBox
        sort.setItems(FXCollections.observableArrayList(sortings));
    }

}
