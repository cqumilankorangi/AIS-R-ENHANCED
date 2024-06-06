/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ais.client;

import com.ais.client.constraint.Constraints;
import com.ais.model.RecruitModel;
import com.ais.util.Session;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    private Pane AddRecruitsPane;

    @FXML
    private Pane DashboardPane;

    @FXML
    private Pane ManagementPane;

    @FXML
    private Pane ReportPane;

    @FXML
    private Pane UpdateRecruitsPane;

    @FXML
    private Button add_recruits;

    @FXML
    private ComboBox<String> cmbDepartment;

    @FXML
    private Button dashboard;

    @FXML
    private Button management;

    @FXML
    private Button report;

    @FXML
    private ComboBox<String> sort;

    @FXML
    private Button update_recruits;

    @FXML
    private TextField txtAddAddress;

    @FXML
    private TextField txtAddEmail;

    @FXML
    private TextField txtAddFullName;

    @FXML
    private TextField txtAddPassword;

    @FXML
    private TextField txtAddPhoneNo;

    @FXML
    private TextField txtAddUserName;

    @FXML
    private DatePicker dtPkAddInteviewDate;

    @FXML
    private ComboBox<String> cmbAddQualification;

    @FXML
    private TextField txtUpdAddress;

    @FXML
    private TextField txtUpdEmail;

    @FXML
    private TextField txtUpdFullName;

    @FXML
    private TextField txtUpdPassword;

    @FXML
    private TextField txtUpdPhoneNo;

    @FXML
    private TextField txtUpdSearchUserName;

    @FXML
    private TextField txtUpdUserName;

    @FXML
    private DatePicker dtPkUpdInterviewDate;

    @FXML
    private ComboBox<String> cmbUpdQualification;

    private Socket socket = null;
    private ObjectInputStream in = null;
    private DataOutputStream out = null;
    private ObjectOutputStream outObj = null;
    private int recruitId;
    private int managementId;
    private int adminId;

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
    void saveRecruitHandler(ActionEvent event) throws IOException {
        try {
            initSocket();

            out.writeUTF("REGISTER");
            out.flush();

            RecruitModel recruit = new RecruitModel(txtAddFullName.getText(), txtAddAddress.getText(),
                    txtAddPhoneNo.getText(), txtAddEmail.getText(), txtAddUserName.getText(),
                    txtAddPassword.getText(), dtPkAddInteviewDate.getValue().format(DateTimeFormatter.ISO_DATE),
                    cmbAddQualification.getValue(), null);

            outObj.writeObject(recruit);
            outObj.flush();

            resetAddRecruit();

        } finally {
            finalCall();
        }
    }

    @FXML
    void searchRecruitHandler(ActionEvent event) throws IOException {
        try {
            RecruitModel authenticate = new RecruitModel(txtUpdSearchUserName.getText());
            initSocket();
            out.writeUTF("GET_RECRUIT_USER_NAME");
            out.flush();
            outObj.writeObject(authenticate);
            outObj.flush();
            RecruitModel recruit = (RecruitModel) in.readObject();
            System.out.println("Object => " + recruit.toString());
            if (recruit != null) {
                recruitId = recruit.getId();
                txtUpdFullName.setText(recruit.getFullName());
                txtUpdAddress.setText(recruit.getAddress());
                txtUpdUserName.setText(recruit.getUserName());
                txtUpdPassword.setText(recruit.getPassword());
                txtUpdPhoneNo.setText(recruit.getPhoneNo());
                txtUpdEmail.setText(recruit.getEmail());
                LocalDate datetime = LocalDate.parse(recruit.getInterviewDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                dtPkUpdInterviewDate.setValue(datetime);
                cmbUpdQualification.setValue(recruit.getQualificationLevel());
                cmbDepartment.setValue(recruit.getDepartment());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            finalCall();
        }
    }

    @FXML
    void updateRecruitHandler(ActionEvent event) throws IOException {
        try {
            initSocket();

            out.writeUTF("UPDATE");
            out.flush();

            RecruitModel recruit = new RecruitModel(recruitId, txtUpdFullName.getText(), txtUpdAddress.getText(),
                    txtUpdPhoneNo.getText(), txtUpdEmail.getText(), txtUpdUserName.getText(),
                    txtUpdPassword.getText(), dtPkUpdInterviewDate.getValue().format(DateTimeFormatter.ISO_DATE),
                    cmbUpdQualification.getValue(), cmbDepartment.getValue());

            outObj.writeObject(recruit);
            outObj.flush();

            resetUpdateRecruit();
        } finally {
            finalCall();
        }
    }

    @FXML
    void logoutHandler(ActionEvent event) throws IOException {
        recruitId = 0;
        managementId = 0;
        App.setRoot("Login");
        Session.getInstance().setAdmin(null);
        Session.getInstance().setManagement(null);
        Session.getInstance().setRecruit(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] sortings = {"Lastname Dec and Groupby Locaton", "Highest level of Qualification"};
        String[] departments = {"Software", "Aerospace", "Mechanical", "Electronics Engineering"};
        String[] qualifications = {"Bachelors", "Masters", "PhD"};

        // Set the items to the ComboBox
        cmbAddQualification.setItems(FXCollections.observableArrayList(qualifications));
        cmbUpdQualification.setItems(FXCollections.observableArrayList(qualifications));
        // Set the items to the ComboBox
        sort.setItems(FXCollections.observableArrayList(sortings));
        cmbDepartment.setItems(FXCollections.observableArrayList(departments));
    }

    private void initSocket() throws IOException {
        socket = new Socket(Constraints.SERVER_URL, Constraints.SERVER_PORT);
        out = new DataOutputStream(socket.getOutputStream());
        outObj = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    private void finalCall() throws IOException {
        if (in != null) {
            in.close();
        }
        if (outObj != null) {
            outObj.close();
        }
        if (out != null) {
            out.close();
        }
        if (socket != null) {
            socket.close();
        }
    }

    private void resetUpdateRecruit() {
        txtUpdFullName.setText(null);
        txtUpdAddress.setText(null);
        txtUpdPhoneNo.setText(null);
        txtUpdEmail.setText(null);
        txtUpdUserName.setText(null);
        txtUpdPassword.setText(null);
        dtPkUpdInterviewDate.setValue(null);
        cmbUpdQualification.setValue("Highest Qualification Level");
        cmbDepartment.setValue("Department");
    }

    private void resetAddRecruit() {
        txtAddFullName.setText(null);
        txtAddAddress.setText(null);
        txtAddPhoneNo.setText(null);
        txtAddEmail.setText(null);
        txtAddUserName.setText(null);
        txtAddPassword.setText(null);
        dtPkAddInteviewDate.setValue(null);
        cmbAddQualification.setValue("Highest Qualification Level");
    }
}
