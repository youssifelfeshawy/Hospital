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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NurseCon implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private int id;

	@FXML
	private Button addB;

	@FXML
	private Button backB;

	@FXML
	private ComboBox<Integer> departmentCB;

	@FXML
	private TableColumn<Nurse, Integer> departmentIdTC;

	@FXML
	private Button editB;

	@FXML
	private TextField phoneNumberTF;

	@FXML
	private TableColumn<Nurse, String> firstNameTC;

	@FXML
	private TextField firstNameTF;

	@FXML
	private TableColumn<Nurse, Integer> idTC;

	@FXML
	private TableColumn<Nurse, String> lastNameTC;

	@FXML
	private TextField lastNameTF;

	@FXML
	private TableColumn<Nurse, String> middleNameTC;

	@FXML
	private TextField middleNameTF;

	@FXML
	private TableView<Nurse> nurseTV;

	@FXML
	private TableColumn<Nurse, String> phoneNumberTC;

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
			String insertTableSQL = "INSERT INTO Nurse (Dept_ID, Phone_NO, F_Name, M_Name, L_Name) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, String.valueOf(departmentCB.getValue()));
			preparedStatement.setString(2, phoneNumberTF.getText());
			preparedStatement.setString(3, firstNameTF.getText());
			preparedStatement.setString(4, middleNameTF.getText());
			preparedStatement.setString(5, lastNameTF.getText());

			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Added Successfully");

			UpdateTable();
			reset();

		} catch (SQLException ex) {
			Logger.getLogger(NurseCon.class.getName()).log(Level.SEVERE, null, ex);
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
			Logger.getLogger(NurseCon.class.getName()).log(Level.SEVERE, null, ex);
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
			String sql = "DELETE FROM Nurse WHERE Nurse_ID=" + this.id;
			message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Deleted successfully</div></body></html>";
			messageLabel = new JLabel(message);
			JOptionPane.showMessageDialog(null, messageLabel, "Clarify", JOptionPane.INFORMATION_MESSAGE);
			try {
				statement.executeUpdate(sql);

				UpdateTable();
				reset();
			} catch (SQLException ex) {
				Logger.getLogger(NurseCon.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@FXML
	void rowClicked(MouseEvent event) {
		Nurse clicked = nurseTV.getSelectionModel().getSelectedItem();
		this.id = clicked.getId();
		departmentCB.setValue(clicked.getDepartmentId());
		phoneNumberTF.setText(String.valueOf(clicked.getPhoneNumber()));
		firstNameTF.setText(String.valueOf(clicked.getFirstName()));
		middleNameTF.setText(String.valueOf(clicked.getMiddleName()));
		lastNameTF.setText(String.valueOf(clicked.getLastName()));

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
				String insertTableSQL = "UPDATE Nurse SET Dept_ID=?, Phone_No=?, F_Name=?, M_Name=?, L_Name=? WHERE Nurse_ID=?";

				PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, String.valueOf(departmentCB.getValue()));
				preparedStatement.setString(2, phoneNumberTF.getText());
				preparedStatement.setString(3, firstNameTF.getText());
				preparedStatement.setString(4, middleNameTF.getText());
				preparedStatement.setString(5, lastNameTF.getText());
				preparedStatement.setString(6, String.valueOf(this.id));

				preparedStatement.executeUpdate();
				message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Edited successfully</div></body></html>";
				messageLabel = new JLabel(message);
				JOptionPane.showMessageDialog(null, messageLabel, "Clarify", JOptionPane.INFORMATION_MESSAGE);

				UpdateTable();
				reset();
			} catch (SQLException ex) {
				Logger.getLogger(NurseCon.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	ObservableList<Nurse> listM;
	int index = -1;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public void UpdateTable() {
		idTC.setCellValueFactory(new PropertyValueFactory<Nurse, Integer>("id"));
		departmentIdTC.setCellValueFactory(new PropertyValueFactory<Nurse, Integer>("departmentId"));
		phoneNumberTC.setCellValueFactory(new PropertyValueFactory<Nurse, String>("phoneNumber"));
		firstNameTC.setCellValueFactory(new PropertyValueFactory<Nurse, String>("firstName"));
		middleNameTC.setCellValueFactory(new PropertyValueFactory<Nurse, String>("middleName"));
		lastNameTC.setCellValueFactory(new PropertyValueFactory<Nurse, String>("lastName"));

		listM = Nurse.getNurseData();
		nurseTV.setItems(listM);

		try {
			Statement statement;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();
			statement = dbConnection.createStatement();
			String sql = "Select * FROM Nurse";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				departmentCB.getItems().add(rs.getInt("Dept_ID"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(NurseCon.class.getName()).log(Level.SEVERE, null, ex);
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
		departmentCB.getSelectionModel().clearSelection();
		departmentCB.getEditor().clear();
		phoneNumberTF.setText("");
		firstNameTF.setText("");
		middleNameTF.setText("");
		lastNameTF.setText("");
	}

	@FXML
	void reset(ActionEvent event) {
		reset();
	}
}
