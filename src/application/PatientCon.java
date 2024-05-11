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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PatientCon implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private int id;

	@FXML
	private Button addB;

	@FXML
	private TableColumn<Patient, String> addressTC;

	@FXML
	private TextField addressTF;

	@FXML
	private TableColumn<Patient, Integer> ageTC;

	@FXML
	private TextField ageTF;

	@FXML
	private Button backB;

	@FXML
	private ComboBox<String> bloodTypeCB;

	@FXML
	private TableColumn<Patient, String> bloodTypeTC;

	@FXML
	private Button editB;

	@FXML
	private TableColumn<Patient, String> firstNameTC;

	@FXML
	private TextField firstNameTF;

	@FXML
	private ComboBox<String> genderCB;

	@FXML
	private TableColumn<Patient, String> genderTC;

	@FXML
	private TableColumn<Patient, Integer> idTC;

	@FXML
	private TableColumn<Patient, String> lastNameTC;

	@FXML
	private TextField lastNameTF;

	@FXML
	private TextArea medicalHistoryTA;

	@FXML
	private TableColumn<Patient, String> medicalHistoryTC;

	@FXML
	private TableColumn<Patient, String> middleNameTC;

	@FXML
	private TextField middleNameTF;

	@FXML
	private TableView<Patient> patientTV;

	@FXML
	private TableColumn<Patient, String> phoneNumberTC;

	@FXML
	private TextField phoneNumberTF;

	@FXML
	private Button removeB;

	@FXML
	private Button resetB;

	@FXML
	void add(ActionEvent event) {
		Statement statement = null;
		Connection dbConnection;
		MyConnection mc = new MyConnection();
		dbConnection = mc.getconConnection();
		try {
			statement = dbConnection.createStatement();
			String insertTableSQL = "INSERT INTO Patient (Phone_No, F_Name, M_Name, L_Name, Address, Gender, Age, Blood_Type, Medical_History) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, phoneNumberTF.getText());
			preparedStatement.setString(2, firstNameTF.getText());
			preparedStatement.setString(3, middleNameTF.getText());
			preparedStatement.setString(4, lastNameTF.getText());
			preparedStatement.setString(5, addressTF.getText());
			String gender = (genderCB.getValue() != null) ? String.valueOf(genderCB.getValue()) : "gender";
			preparedStatement.setString(6, gender);
//			preparedStatement.setString(6, String.valueOf(genderCB.getValue()));
			preparedStatement.setString(7, ageTF.getText());
			String bloodType = (bloodTypeCB.getValue() != null) ? String.valueOf(bloodTypeCB.getValue()) : "blood";
			preparedStatement.setString(8, bloodType);

//			preparedStatement.setString(8, String.valueOf(bloodTypeCB.getValue()));
			preparedStatement.setString(9, medicalHistoryTA.getText());

			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Added Successfully");

			UpdateTable();
			reset();

		} catch (SQLException ex) {
			Logger.getLogger(PatientCon.class.getName()).log(Level.SEVERE, null, ex);
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
			Logger.getLogger(PatientCon.class.getName()).log(Level.SEVERE, null, ex);
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
			String sql = "DELETE FROM Patient WHERE Patient_ID=" + this.id;
			message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Deleted successfully</div></body></html>";
			messageLabel = new JLabel(message);
			JOptionPane.showMessageDialog(null, messageLabel, "Clarify", JOptionPane.INFORMATION_MESSAGE);
			try {
				statement.executeUpdate(sql);

				UpdateTable();
				reset();
			} catch (SQLException ex) {
				Logger.getLogger(PatientCon.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@FXML
	void rowClicked(MouseEvent event) {
		Patient clicked = patientTV.getSelectionModel().getSelectedItem();
		this.id = clicked.getId();
		firstNameTF.setText(String.valueOf(clicked.getFirstName()));
		middleNameTF.setText(String.valueOf(clicked.getMiddleName()));
		lastNameTF.setText(String.valueOf(clicked.getLastName()));
		phoneNumberTF.setText(String.valueOf(clicked.getPhoneNumber()));
		addressTF.setText(String.valueOf(clicked.getAddress()));
		genderCB.setValue(clicked.getGender());
		ageTF.setText(String.valueOf(clicked.getAge()));
		bloodTypeCB.setValue(clicked.getBloodType());
		medicalHistoryTA.setText(String.valueOf(clicked.getMedicalHistory()));

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
			Logger.getLogger(PatientCon.class.getName()).log(Level.SEVERE, null, ex);
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
				String insertTableSQL = "UPDATE Patient SET Phone_No=?, F_Name=?, M_Name=?, L_Name=?, Address=?, Gender=?, Age=?, Blood_Type=?, Medical_History=? WHERE Patient_ID=?";

				PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, phoneNumberTF.getText());
				preparedStatement.setString(2, firstNameTF.getText());
				preparedStatement.setString(3, middleNameTF.getText());
				preparedStatement.setString(4, lastNameTF.getText());
				preparedStatement.setString(5, addressTF.getText());
				preparedStatement.setString(6, String.valueOf(genderCB.getValue()));
				preparedStatement.setString(7, ageTF.getText());
				preparedStatement.setString(8, String.valueOf(bloodTypeCB.getValue()));
				preparedStatement.setString(9, medicalHistoryTA.getText());
				preparedStatement.setString(10, String.valueOf(this.id));

				preparedStatement.executeUpdate();
				message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Edited successfully</div></body></html>";
				messageLabel = new JLabel(message);
				JOptionPane.showMessageDialog(null, messageLabel, "Clarify", JOptionPane.INFORMATION_MESSAGE);

				UpdateTable();
				reset();
			} catch (SQLException ex) {
				Logger.getLogger(PatientCon.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	ObservableList<Patient> listM;
	int index = -1;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public void UpdateTable() {
		idTC.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
		firstNameTC.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
		middleNameTC.setCellValueFactory(new PropertyValueFactory<Patient, String>("middleName"));
		lastNameTC.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
		phoneNumberTC.setCellValueFactory(new PropertyValueFactory<Patient, String>("phoneNumber"));
		addressTC.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
		genderTC.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
		ageTC.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
		bloodTypeTC.setCellValueFactory(new PropertyValueFactory<Patient, String>("bloodType"));
		medicalHistoryTC.setCellValueFactory(new PropertyValueFactory<Patient, String>("medicalHistory"));

		listM = Patient.getPatientData();
		patientTV.setItems(listM);
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
		genderCB.getItems().addAll("Male", "Female");
		bloodTypeCB.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
	}

	public void reset() {
		phoneNumberTF.setText("");
		firstNameTF.setText("");
		middleNameTF.setText("");
		lastNameTF.setText("");
		addressTF.setText("");
		genderCB.getSelectionModel().clearSelection();
		genderCB.getEditor().clear();
		ageTF.setText("");
		bloodTypeCB.getSelectionModel().clearSelection();
		bloodTypeCB.getEditor().clear();
		medicalHistoryTA.setText("");
	}

	@FXML
	void reset(ActionEvent event) {
		reset();
	}
}
