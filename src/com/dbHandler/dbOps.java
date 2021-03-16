package com.dbHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class dbOps {

	public  Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/l_academy", "Scott", "tiger");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public void deleteClassAssociation(int subId) {
		Connection con = getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement("delete from class_subjects where SUB_IDS =?");
			preparedStatement.setInt(1, subId);
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			System.err.println("Could not delete class association for the subjects");
			e1.printStackTrace();
		}

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public int deleteClassAssSpec(int subId, int cid) {
		Connection con = getConnection();
		PreparedStatement preparedStatement;
		PreparedStatement ps;
		int row = 0;
		try {
			preparedStatement = con.prepareStatement("delete from class_subjects where SUB_IDS =? and CLASS_ID = ?");
			preparedStatement.setInt(1, subId);
			preparedStatement.setInt(2, cid);
			preparedStatement.executeUpdate();
			
			ps = con.prepareStatement("select count(*) from class_subjects where SUB_IDS =?");
			ps.setInt(1, subId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			row=rs.getInt("count(*)");
			System.out.println("Row " + row);
			return row;
			
		} catch (SQLException e1) {
			System.err.println("Could not delete class association for the subjects");
			e1.printStackTrace();
			return row;
		}

		
	}

	public void deleteSubClassAssociation(int facId) {
		Connection con = getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement("delete from teacher_subs where TEACHER_ID =?");
			preparedStatement.setInt(1, facId);
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			System.err.println("Could not delete class-subject association for the subjects");
			e1.printStackTrace();
		}

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteClassDependencies(int cid) {
		Connection con = getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement("SET SQL_SAFE_UPDATES = 0;");
			preparedStatement.executeUpdate();
			
			preparedStatement = con.prepareStatement("delete from teacher_subs where SUB_CLASS_ID in (select assId from subjects_class where CLASS_ID =?);");
			preparedStatement.setInt(1, cid);
			preparedStatement.executeUpdate();
			
			preparedStatement = con.prepareStatement("update studs SET CLASS_ID = null where CLASS_ID =?;");
			preparedStatement.setInt(1, cid);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e1) {
			System.err.println("Could not delete class-subject association for the subjects");
			e1.printStackTrace();
		}
	}
}
