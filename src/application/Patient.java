package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Patient {

	private int id, age;
	private String firstName, middleName, lastName, phoneNumber, address, gender, bloodType, medicalHistory;

	public Patient(int id, String firstName, String middleName, String lastName, String phoneNumber, String gender,
			int age, String address, String bloodType, String medicalHistory) {
		super();
		this.id = id;
		this.age = age;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.gender = gender;
		this.bloodType = bloodType;
		this.medicalHistory = medicalHistory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public static ObservableList<Patient> getPatientData() {
		ObservableList<Patient> list = FXCollections.observableArrayList();
		try {
			Statement statement = null;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();

			statement = dbConnection.createStatement();
			String sql = "select * from Patient";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				list.add(new Patient(Integer.parseInt(rs.getString("Patient_ID")), rs.getString("F_Name"),
						rs.getString("M_Name"), rs.getString("L_Name"), rs.getString("Phone_No"),
						rs.getString("Gender"), Integer.parseInt(rs.getString("Age")), rs.getString("Address"),
						rs.getString("Blood_Type"), rs.getString("Medical_History")));
			}
		} catch (Exception e) {

		}
		return list;
	}

}
