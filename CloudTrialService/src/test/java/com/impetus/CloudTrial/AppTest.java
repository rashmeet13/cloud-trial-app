package com.impetus.CloudTrial;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.impetus.CloudTrialBusiness.model.UserEntity;
import com.impetus.CloudTrialBusiness.processors.UserRegistration;

public class AppTest {
	private static String dbURL = "jdbc:derby://localhost:1527/CloudTrialDB";
	private static String tableName = "REGISTRATION_DETAILS";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	public static void main(String[] args) {
		// createConnection();
		// insertRestaurants(5, "nidhi.upadhyayay@in.com", "small", "testTerm",
		// "testregion", "testsource");
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime()
				.getTime());
		UserRegistration userReg = new UserRegistration();
		UserEntity user = new com.impetus.CloudTrialBusiness.model.UserEntity();
		user.setEmail("abc@xyz.com");
		user.setRegion("TestRegion");
		user.setSource("TestSource");
		user.setTerm("Testterm");
		user.setStartDate(currentTimestamp);
		user.setType("small");
		System.out.println(userReg.processUserRegistration(user));

	}

	private static void createConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			// Get a connection
			conn = DriverManager.getConnection(dbURL);
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	private static void insertRestaurants(int id, String email, String type,
			String term, String region, String source) {
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime()
				.getTime());
		try {
			stmt = conn.createStatement();
			stmt.execute("insert into " + tableName + " values (" + id + ",'"
					+ email + "','" + type + "','" + currentTimestamp + "','"
					+ term + "','" + region + "','" + source + "')");

			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();
			for (int i = 1; i <= numberCols; i++) {
				// print Column Names
				System.out.print(rsmd.getColumnLabel(i) + "\t\t");
			}

			System.out
					.println("\n-------------------------------------------------");

			while (results.next()) {
				int idtet = results.getInt(1);
				String emailId = results.getString(2);
				String typeId = results.getString(3);
				System.out.println(idtet + "\t\t" + emailId + "\t\t" + typeId);
			}
			results.close();
			stmt.close();
			conn.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}
}
