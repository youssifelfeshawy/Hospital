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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DepartmentCon implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private int id;

	@FXML
	private Button addB;

	@FXML
	private Button backB;

	@FXML
	private TextArea departmentDescriptionTA;

	@FXML
	private TextField departmentHeadTF;

	@FXML
	private TextField departmentNameTF;

	@FXML
	private TableView<Department> departmentTV;

	@FXML
	private TableColumn<Department, String> descriptionTC;

	@FXML
	private Button editB;

	@FXML
	private TableColumn<Department, String> headTC;

	@FXML
	private TableColumn<Department, Integer> idTC;

	@FXML
	private TableColumn<Department, String> nameTC;

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
			String insertTableSQL = "INSERT INTO Department (Dept_Name, Dept_Desc, Dept_Head) VALUES (?, ?, ?)";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, departmentNameTF.getText());
			preparedStatement.setString(2, departmentDescriptionTA.getText());
			preparedStatement.setString(3, departmentHeadTF.getText());

			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Added Successfully");

			UpdateTable();
			reset();

		} catch (SQLException ex) {
			Logger.getLogger(DepartmentCon.class.getName()).log(Level.SEVERE, null, ex);
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
			Logger.getLogger(DepartmentCon.class.getName()).log(Level.SEVERE, null, ex);
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
			String sql = "DELETE FROM Department WHERE Dept_ID=" + this.id;
			message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Deleted successfully</div></body></html>";
			messageLabel = new JLabel(message);
			JOptionPane.showMessageDialog(null, messageLabel, "Clarify", JOptionPane.INFORMATION_MESSAGE);
			try {
				statement.executeUpdate(sql);

				UpdateTable();
				reset();
			} catch (SQLException ex) {
				Logger.getLogger(DepartmentCon.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@FXML
	void rowClicked(MouseEvent event) {
		Department clicked = departmentTV.getSelectionModel().getSelectedItem();
		this.id = clicked.getId();
		departmentNameTF.setText(String.valueOf(clicked.getDepartmentName()));
		departmentDescriptionTA.setText(String.valueOf(clicked.getDepartmentDescription()));
		departmentHeadTF.setText(String.valueOf(clicked.getDepartmentHead()));
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
				String insertTableSQL = "UPDATE Department SET Dept_Name=?, Dept_Desc=?, Dept_Head=? WHERE Dept_ID=?";

				PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, departmentNameTF.getText());
				preparedStatement.setString(2, departmentDescriptionTA.getText());
				preparedStatement.setString(3, departmentHeadTF.getText());
				preparedStatement.setString(4, String.valueOf(this.id));

				preparedStatement.executeUpdate();
				message = "<html><body><div width='200px' align='right' style='font-size:12px;color:blue;'>Edited successfully</div></body></html>";
				messageLabel = new JLabel(message);
				JOptionPane.showMessageDialog(null, messageLabel, "Clarify", JOptionPane.INFORMATION_MESSAGE);

				UpdateTable();
				reset();
			} catch (SQLException ex) {
				Logger.getLogger(DepartmentCon.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	ObservableList<Department> listM;
	int index = -1;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public void UpdateTable() {
		idTC.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
		nameTC.setCellValueFactory(new PropertyValueFactory<Department, String>("departmentName"));
		descriptionTC.setCellValueFactory(new PropertyValueFactory<Department, String>("departmentDescription"));
		headTC.setCellValueFactory(new PropertyValueFactory<Department, String>("departmentHead"));

		listM = Department.getDepartmentData();
		departmentTV.setItems(listM);
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
		departmentNameTF.setText("");
		departmentDescriptionTA.setText("");
		departmentHeadTF.setText("");
	}

	@FXML
	void reset(ActionEvent event) {
		reset();
	}
}
