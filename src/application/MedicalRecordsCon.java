package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MedicalRecordsCon implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private int id;

	@FXML
	private TableView<MedicalRecords> medicalRecordsTV;

	@FXML
	private Button addB;

	@FXML
	private Button backB;

	@FXML
	private TableColumn<MedicalRecords, String> diagnosisTC;

	@FXML
	private TextArea diagnosisTA;

	@FXML
	private Button editB;

	@FXML
	private TableColumn<MedicalRecords, Integer> idTC;

	@FXML
	private ComboBox<Integer> medicationIdCB;

	@FXML
	private TableColumn<MedicalRecords, Integer> medicationIdTC;

	@FXML
	private ComboBox<Integer> patientIdCB;

	@FXML
	private TableColumn<MedicalRecords, Integer> patientIdTC;

	@FXML
	private Button removeB;

	@FXML
	private Button resetB;

	@FXML
	private TableColumn<MedicalRecords, String> treatmentHistoryTC;

	@FXML
	private TextArea treatmentHistoryTA;

	@FXML
	void add(ActionEvent event) {
		Statement statement = null;
		Connection dbConnection;
		MyConnection mc = new MyConnection();
		dbConnection = mc.getconConnection();
		try {
			statement = dbConnection.createStatement();
			String insertTableSQL = "INSERT INTO Medical_Records (Patient_ID, Medication_ID, Diagnosis, Treatment_History) VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, String.valueOf(patientIdCB.getValue()));
			preparedStatement.setString(2, String.valueOf(medicationIdCB.getValue()));
			preparedStatement.setString(3, diagnosisTA.getText());
			preparedStatement.setString(4, treatmentHistoryTA.getText());

			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Added Successfully");

			UpdateTable();
			reset();

		} catch (SQLException ex) {
			Logger.getLogger(MedicalRecordsCon.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	void remove(ActionEvent event) {
		Statement statement = null;
		Connection dbConnection = null;
		boolean del;
		MyConnection mc = new MyConnection();
		dbConnection = mc.getconConnection();
		try {
			statement = dbConnection.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(MedicalRecordsCon.class.getName()).log(Level.SEVERE, null, ex);
		}
		String message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Do you want to delete?</div></body></html>";
		JLabel messageLabel = new JLabel(message);
		String[] options = new String[2];
		options[0] = new String("Yes");
		options[1] = new String("No");
		int answer = JOptionPane.showOptionDialog(null, messageLabel, "Confirm delete", 0,
				JOptionPane.INFORMATION_MESSAGE, null, options, null);
		if (answer == JOptionPane.YES_OPTION) {
			del = true;
		} else {
			del = false;
		}
		if (del) {
			String sql = "DELETE FROM Medical_Records WHERE Medical_Records_ID=" + this.id;
			message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Deleted successfully</div></body></html>";
			messageLabel = new JLabel(message);
			JOptionPane.showMessageDialog(null, messageLabel, "Clarify", JOptionPane.INFORMATION_MESSAGE);
			try {
				statement.executeUpdate(sql);

				UpdateTable();
				reset();
			} catch (SQLException ex) {
				Logger.getLogger(MedicalRecordsCon.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@FXML
	void rowClicked(MouseEvent event) {
		MedicalRecords clicked = medicalRecordsTV.getSelectionModel().getSelectedItem();
		this.id = clicked.getId();
		patientIdCB.setValue(clicked.getPatientId());
		medicationIdCB.setValue(clicked.getMedicationId());
		diagnosisTA.setText(String.valueOf(clicked.getDiagnosis()));
		treatmentHistoryTA.setText(String.valueOf(clicked.getTreatmentHistory()));
	}

	@FXML
	void edit(ActionEvent event) {
		Statement statement = null;
		Connection dbConnection = null;
		MyConnection mc = new MyConnection();
		dbConnection = mc.getconConnection();
		try {
			statement = dbConnection.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(MedicalRecordsCon.class.getName()).log(Level.SEVERE, null, ex);
		}
		String message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Are you sure you want to edit?</div></body></html>";
		JLabel messageLabel = new JLabel(message);
		String[] options = new String[2];
		options[0] = new String("Yes");
		options[1] = new String("No");
		int answer = JOptionPane.showOptionDialog(null, messageLabel, "Confirm edit", 0,
				JOptionPane.INFORMATION_MESSAGE, null, options, null);
		boolean confirm;
		if (answer == JOptionPane.YES_OPTION) {
			confirm = true;
		} else {
			confirm = false;
		}
		if (confirm) {
			try {
				statement = dbConnection.createStatement();
				String insertTableSQL = "UPDATE Medical_Records SET Patient_ID=?, Medication_ID=?, Diagnosis=?, Treatment_History=? WHERE Medical_Records_ID=?";

				PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, String.valueOf(patientIdCB.getValue()));
				preparedStatement.setString(2, String.valueOf(medicationIdCB.getValue()));
				preparedStatement.setString(3, diagnosisTA.getText());
				preparedStatement.setString(4, treatmentHistoryTA.getText());
				preparedStatement.setString(5, String.valueOf(this.id));

				preparedStatement.executeUpdate();
				message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Edited successfully</div></body></html>";
				messageLabel = new JLabel(message);
				JOptionPane.showMessageDialog(null, messageLabel, "Clarify", JOptionPane.INFORMATION_MESSAGE);

				UpdateTable();
				reset();
			} catch (SQLException ex) {
				Logger.getLogger(MedicalRecordsCon.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	ObservableList<MedicalRecords> listM;
	int index = -1;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public void UpdateTable() {
		idTC.setCellValueFactory(new PropertyValueFactory<MedicalRecords, Integer>("id"));
		patientIdTC.setCellValueFactory(new PropertyValueFactory<MedicalRecords, Integer>("patientId"));
		medicationIdTC.setCellValueFactory(new PropertyValueFactory<MedicalRecords, Integer>("medicationId"));
		diagnosisTC.setCellValueFactory(new PropertyValueFactory<MedicalRecords, String>("diagnosis"));
		treatmentHistoryTC.setCellValueFactory(new PropertyValueFactory<MedicalRecords, String>("treatmentHistory"));

		listM = MedicalRecords.getMedicalRecordsData();
		medicalRecordsTV.setItems(listM);

		try {
			Statement statement;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();
			statement = dbConnection.createStatement();
			String sql = "Select * FROM Medical_Records";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				patientIdCB.getItems().add(rs.getInt("Patient_ID"));
				medicationIdCB.getItems().add(rs.getInt("Medication_ID"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(AppointmentCon.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	void back(ActionEvent event) throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		UpdateTable();
	}

	public void reset() {
		patientIdCB.getSelectionModel().clearSelection();
		patientIdCB.getEditor().clear();
		medicationIdCB.getSelectionModel().clearSelection();
		medicationIdCB.getEditor().clear();
		diagnosisTA.setText("");
		treatmentHistoryTA.setText("");
	}

	@FXML
	void reset(ActionEvent event) {
		reset();
	}
}
