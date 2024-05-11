package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MedicalRecords {

	private int id, patientId, medicationId;
	private String diagnosis, treatmentHistory;

	public MedicalRecords(int id, int patientId, int medicationId, String diagnosis, String treatmentHistory) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.medicationId = medicationId;
		this.diagnosis = diagnosis;
		this.treatmentHistory = treatmentHistory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatmentHistory() {
		return treatmentHistory;
	}

	public void setTreatmentHistory(String treatmentHistory) {
		this.treatmentHistory = treatmentHistory;
	}

	public static ObservableList<MedicalRecords> getMedicalRecordsData() {
		ObservableList<MedicalRecords> list = FXCollections.observableArrayList();
		try {
			Statement statement = null;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();

			statement = dbConnection.createStatement();
			String sql = "select * from Medical_Records";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				list.add(new MedicalRecords(Integer.parseInt(rs.getString("Medical_Records_ID")),
						Integer.parseInt(rs.getString("Patient_ID")), Integer.parseInt(rs.getString("Medication_ID")),
						rs.getString("Diagnosis"), rs.getString("Treatment_History")));
			}
		} catch (Exception e) {

		}
		return list;
	}
}
