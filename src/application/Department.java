package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Department {

	private int id;
	private String departmentName, departmentDescription, departmentHead;

	public Department(int id, String departmentName, String departmentDescription, String departmentHead) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.departmentDescription = departmentDescription;
		this.departmentHead = departmentHead;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	public String getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(String departmentHead) {
		this.departmentHead = departmentHead;
	}

	public static ObservableList<Department> getDepartmentData() {
		ObservableList<Department> list = FXCollections.observableArrayList();
		try {
			Statement statement = null;
			Connection dbConnection;
			MyConnection mc = new MyConnection();
			dbConnection = mc.getconConnection();

			statement = dbConnection.createStatement();
			String sql = "select * from Department";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				list.add(new Department(Integer.parseInt(rs.getString("Dept_ID")), rs.getString("Dept_Name"),
						rs.getString("Dept_Desc"), rs.getString("Dept_Head")));
			}
		} catch (Exception e) {

		}
		return list;
	}

}
