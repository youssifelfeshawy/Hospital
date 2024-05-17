package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Appointment {
	private int id, patientId, doctorId, roomNumber;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;

	public Appointment(int id, int patientId, int doctorId, int roomNumber, LocalDate appointmentDate,
			LocalTime appointmentTime) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.roomNumber = roomNumber;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
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

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public static ObservableList<Appointment> getAppointmentData() {
		ObservableList<Appointment> list = FXCollections.observableArrayList();
		try {
			Statement statement = null;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();

			statement = dbConnection.createStatement();
			String sql = "Select * FROM Appointment";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				list.add(new Appointment(Integer.parseInt(rs.getString("Apt_ID")),
						Integer.parseInt(rs.getString("Patient_ID")), Integer.parseInt(rs.getString("Doctor_ID")),
						Integer.parseInt(rs.getString("Room_No")),
						LocalDate.parse(rs.getString("Appt_Date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						LocalTime.parse(rs.getString("Appt_Time"), DateTimeFormatter.ofPattern("HH:mm:ss"))));
			}
		} catch (Exception e) {

		}
		return list;
	}

}
