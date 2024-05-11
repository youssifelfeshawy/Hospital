package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Doctor {
	private int id, departmentId;
	private String phoneNumber, firstName, middleName, lastName;

	public Doctor(int id, int departmentId, String phoneNumber, String firstName, String middleName, String lastName) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public static ObservableList<Doctor> getDoctorData() {
		ObservableList<Doctor> list = FXCollections.observableArrayList();
		try {
			Statement statement = null;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();

			statement = dbConnection.createStatement();
			String sql = "select * from Doctor";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				list.add(new Doctor(Integer.parseInt(rs.getString("Doctor_ID")),
						Integer.parseInt(rs.getString("Dept_ID")), rs.getString("Phone_No"), rs.getString("F_Name"),
						rs.getString("M_Name"), rs.getString("L_Name")));
			}
		} catch (Exception e) {

		}
		return list;
	}

}
