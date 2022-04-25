package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electrimanage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String user_name, String email, String address, String phone_num, String password) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user1(`user_id`,`user_name`,`email`,`address`,`phone_num`,`password`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, user_name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, phone_num);
			preparedStmt.setString(6, password);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User ID</th><th>User Name</th><th>Email</th><th>Address</th><th>Phone Num</th><th>Password</th></tr>";
			String query = "select * from user1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String user_id = Integer.toString(rs.getInt("user_id"));
				String user_name = rs.getString("user_name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phone_num = rs.getString("phone_num");
				String password = rs.getString("password");

				// Add into the html table
				output += "<tr><td>" + user_id + "</td>";
				output += "<td>" + user_name + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + phone_num + "</td>";
				output += "<td>" + password + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String user_id, String user_name, String email, String address, String phone_num, String password) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE user1 SET user_name=?,email=?,address=?,phone_num=?,password=?" + "WHERE user_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, user_name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, phone_num);
			preparedStmt.setString(5, password);
			preparedStmt.setInt(6, Integer.parseInt(user_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the user.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteUser(String user_id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from user1 where user_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(user_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the user.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
