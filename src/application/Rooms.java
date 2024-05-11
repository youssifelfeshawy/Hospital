package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Rooms {

	private int id;
	private String roomType, roomAvailibility;

	public Rooms(int id, String roomType, String roomAvailibility) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.roomAvailibility = roomAvailibility;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomAvailibility() {
		return roomAvailibility;
	}

	public void setRoomAvailibility(String roomAvailibility) {
		this.roomAvailibility = roomAvailibility;
	}

	public static ObservableList<Rooms> getRoomsData() {
		ObservableList<Rooms> list = FXCollections.observableArrayList();
		try {
			Statement statement = null;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();

			statement = dbConnection.createStatement();
			String sql = "select * from Rooms";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				list.add(new Rooms(Integer.parseInt(rs.getString("Room_No")), rs.getString("Room_Type"),
						rs.getString("Room_Avail")));
			}
		} catch (Exception e) {

		}
		return list;
	}
}
