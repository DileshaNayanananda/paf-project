package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Inquiry {

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

	public String insertInquiry(String inquiry_name, String inquiry_acc, String inquiry_reason, String inquiry_date, String inquiry_pNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into inquiry1(`inquiryId`,`inquiry_name`,`inquiry_acc`,`inquiry_reason`,`inquiry_date`,`inquiry_pNo`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, inquiry_name);
			preparedStmt.setString(3, inquiry_acc);
			preparedStmt.setString(4, inquiry_reason);
			preparedStmt.setString(5, inquiry_date);
			preparedStmt.setString(6, inquiry_pNo);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the inquiry.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readInquiry() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Inquiry ID</th><th>Customer Name</th><th>Account NO</th><th>Reason</th><th>Date</th><th>Phone Number</th></tr>";
			String query = "select * from inquiry1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String inquiryId = Integer.toString(rs.getInt("inquiryId"));
				String inquiry_name = rs.getString("inquiry_name");
				String inquiry_acc = rs.getString("inquiry_acc");
				String inquiry_reason = rs.getString("inquiry_reason");
				String inquiry_date = rs.getString("inquiry_date");
				String inquiry_pNo = rs.getString("inquiry_pNo");

				// Add into the html table
				output += "<tr><td>" + inquiryId + "</td>";
				output += "<td>" + inquiry_name + "</td>";
				output += "<td>" + inquiry_acc + "</td>";
				output += "<td>" + inquiry_reason + "</td>";
				output += "<td>" + inquiry_date + "</td>";
				output += "<td>" + inquiry_pNo + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the inquiry.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateInquiry(String inquiryId, String inquiry_name, String inquiry_acc, String inquiry_reason, String inquiry_date, String inquiry_pNo) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE inquiry1 SET inquiry_name=?,inquiry_acc=?,inquiry_reason=?,inquiry_date=?,inquiry_pNo=?" + "WHERE inquiryId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, inquiry_name);
			preparedStmt.setString(2, inquiry_acc);
			preparedStmt.setString(3, inquiry_reason);
			preparedStmt.setString(4, inquiry_date);
			preparedStmt.setString(5, inquiry_pNo);
			preparedStmt.setInt(6, Integer.parseInt(inquiryId));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the inquiry.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteInquiry(String inquiryId) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from inquiry1 where inquiryId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(inquiryId));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the inquiry.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
