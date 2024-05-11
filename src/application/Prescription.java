package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Prescription {

	private int Id, medicationId, patientId;

	public Prescription(int Id, int medicationId, int patientId) {
		super();
		this.Id = Id;
		this.medicationId = medicationId;
		this.patientId = patientId;
	}
	

	public int getId() {
		return Id;
	}



	public void setId(int Id) {
		this.Id = Id;
	}



	public int getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public static ObservableList<Prescription> getPrescriptionData() {
		ObservableList<Prescription> list = FXCollections.observableArrayList();
		try {
			Statement statement = null;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();

			statement = dbConnection.createStatement();
			String sql = "select * from Prescription";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				list.add(new Prescription(Integer.parseInt(rs.getString("Prescription_ID")),
						Integer.parseInt(rs.getString("Medication_ID")), Integer.parseInt(rs.getString("Patient_ID"))));
			}
		} catch (Exception e) {

		}
		return list;
	}
}

