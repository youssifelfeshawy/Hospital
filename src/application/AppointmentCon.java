package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AppointmentCon implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private int id;

	@FXML
	private Button addB;

	@FXML
	private TableView<Appointment> appointmentTV;

	@FXML
	private Button backB;

	@FXML
	private DatePicker dateDP;

	@FXML
	private TableColumn<Appointment, Date> dateTC;

	@FXML
	private ComboBox<Integer> doctorIdCB;

	@FXML
	private TableColumn<Appointment, Integer> doctorIdTC;

	@FXML
	private Button editB;

	@FXML
	private TableColumn<Appointment, Integer> idTC;

	@FXML
	private ComboBox<Integer> patientIdCB;

	@FXML
	private TableColumn<Appointment, Integer> patientIdTC;

	@FXML
	private Button removeB;

	@FXML
	private Button resetB;

	@FXML
	private ComboBox<Integer> roomNumberCB;

	@FXML
	private TableColumn<Appointment, Integer> roomNumberTC;

	@FXML
	private TableColumn<Appointment, Time> timeTC;

	@FXML
	private TextField timeTF;

	@FXML
	void add(ActionEvent event) {
		Statement statement = null;
		Connection dbConnection;
		MyConnection mc = new MyConnection();
		dbConnection = mc.getconConnection();
		try {
			statement = dbConnection.createStatement();
			String insertTableSQL = "INSERT INTO Appointment (Patient_ID, Doctor_ID, Room_No, Appt_Date, Appt_Time) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, String.valueOf(patientIdCB.getValue()));
			preparedStatement.setString(2, String.valueOf(doctorIdCB.getValue()));
			preparedStatement.setString(3, String.valueOf(roomNumberCB.getValue()));
			preparedStatement.setString(4, dateDP.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			preparedStatement.setString(5,
					String.valueOf(LocalTime.parse(timeTF.getText(), DateTimeFormatter.ofPattern("HH:mm"))));

			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Added Successfully");

			UpdateTable();
			reset();

		} catch (SQLException ex) {
			Logger.getLogger(AppointmentCon.class.getName()).log(Level.SEVERE, null, ex);
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
			String sql = "DELETE FROM Appointment WHERE Appt_ID=" + this.id;
			message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Deleted successfully</div></body></html>";
			messageLabel = new JLabel(message);
			JOptionPane.showMessageDialog(null, messageLabel, "Clarify", JOptionPane.INFORMATION_MESSAGE);
			try {
				statement.executeUpdate(sql);

				UpdateTable();
				reset();
			} catch (SQLException ex) {
				Logger.getLogger(AppointmentCon.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@FXML
	void rowClicked(MouseEvent event) {
		Appointment clicked = appointmentTV.getSelectionModel().getSelectedItem();
		this.id = clicked.getId();
		patientIdCB.setValue(clicked.getPatientId());
		doctorIdCB.setValue(clicked.getDoctorId());
		roomNumberCB.setValue(clicked.getRoomNumber());
		dateDP.setValue(LocalDate.parse(String.valueOf(clicked.getAppointmentDate()),
				DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		timeTF.setText(String.valueOf(clicked.getAppointmentTime()));
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
			Logger.getLogger(AppointmentCon.class.getName()).log(Level.SEVERE, null, ex);
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
				String insertTableSQL = "UPDATE Appointment SET Patient_ID=?, Doctor_ID=?, Room_No=?, Appt_Date=?, Appt_Time=? WHERE Appt_ID=?";

				PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, String.valueOf(patientIdCB.getValue()));
				preparedStatement.setString(2, String.valueOf(doctorIdCB.getValue()));
				preparedStatement.setString(3, String.valueOf(roomNumberCB.getValue()));
				preparedStatement.setString(4, String.valueOf(dateDP.getValue()));
				preparedStatement.setString(5, timeTF.getText());
				preparedStatement.setString(6, String.valueOf(this.id));

				preparedStatement.executeUpdate();
				message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Edited successfully</div></body></html>";
				messageLabel = new JLabel(message);
				JOptionPane.showMessageDialog(null, messageLabel, "Clarify", JOptionPane.INFORMATION_MESSAGE);

				UpdateTable();
				reset();
			} catch (SQLException ex) {
				Logger.getLogger(AppointmentCon.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	ObservableList<Appointment> listM;
	int index = -1;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public void UpdateTable() {
		idTC.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("id"));
		patientIdTC.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("patientId"));
		doctorIdTC.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("doctorId"));
		roomNumberTC.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("roomNumber"));
		dateTC.setCellValueFactory(new PropertyValueFactory</*Appointment, LocalDate*/>("appointmentDate"));
		timeTC.setCellValueFactory(new PropertyValueFactory</* Appointment, LocalTime */>("appointmentTime"));

		listM = Appointment.getAppointmentData();
		appointmentTV.setItems(listM);

		try {
			Statement statement;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();
			statement = dbConnection.createStatement();
			String sql = "Select * FROM Appointment";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				patientIdCB.getItems().add(rs.getInt("Patient_ID"));
				doctorIdCB.getItems().add(rs.getInt("Doctor_ID"));
				roomNumberCB.getItems().add(rs.getInt("room_No"));
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
		doctorIdCB.getSelectionModel().clearSelection();
		doctorIdCB.getEditor().clear();
		roomNumberCB.getSelectionModel().clearSelection();
		roomNumberCB.getEditor().clear();
		dateDP.setValue(null);
		timeTF.setText("");
	}

	@FXML
	void reset(ActionEvent event) {
		reset();
	}

}
