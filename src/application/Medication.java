package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Medication {
	private int id;
	private String medicationName, dosage;
	private LocalDate expDate;

	public Medication(int id, String medicationName, String dosage, LocalDate expDate) {
		super();
		this.id = id;
		this.medicationName = medicationName;
		this.dosage = dosage;
		this.expDate = expDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public LocalDate getExpDate() {
		return expDate;
	}

	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}

	public static ObservableList<Medication> getMedicationData() {
		ObservableList<Medication> list = FXCollections.observableArrayList();
		try {
			Statement statement = null;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();

			statement = dbConnection.createStatement();
			String sql = "select * from Medication";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				list.add(new Medication(Integer.parseInt(rs.getString("Medication_ID")),
						rs.getString("Medication_Name"), rs.getString("Dosage"),
						LocalDate.parse(rs.getString("Exp_Date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
			}
		} catch (Exception e) {

		}
		return list;
	}
}
