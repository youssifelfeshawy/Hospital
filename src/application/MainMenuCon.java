package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuCon {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button appointmentB;

	@FXML
	private Button departmentB;

	@FXML
	private Button doctorB;

	@FXML
	private Button medicalRecordsB;

	@FXML
	private Button medicationB;

	@FXML
	private Button nurseB;

	@FXML
	private Button patientB;

	@FXML
	private Button prescriptionB;

	@FXML
	private Button roomB;

	@FXML
	void openAppointment(ActionEvent event) throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("Appointment.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openDepartment(ActionEvent event) throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("Department.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openDoctor(ActionEvent event) throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("Doctor.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openMedicalRedcords(ActionEvent event) throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("MedicalRecords.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openMedication(ActionEvent event) throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("Medication.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openNurse(ActionEvent event) throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("Nurse.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openPatient(ActionEvent event) throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("Patient.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openPrescription(ActionEvent event) throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("Prescription.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void openRoom(ActionEvent event) throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("Rooms.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
