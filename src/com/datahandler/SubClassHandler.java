package com.datahandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.dbHandler.dbOps;
import com.entity.SubjectClass;
import com.utility.HibernateUtility;

public class SubClassHandler {

	public List<SubjectClass> getSubClassMapping(int tid) {
		Session session = HibernateUtility.getSession();

		dbOps db = new dbOps();
		Connection con = db.getConnection();
		SubjectClass mp;
		List<SubjectClass> maped = new ArrayList<SubjectClass>();
		try {
			PreparedStatement query = con.prepareStatement(
					"select assId from subjects_class where assId not in (select SUB_CLASS_ID from teacher_subs where TEACHER_ID !="
							+ tid + ")");

			ResultSet rs = query.executeQuery();
			while (rs.next()) {
				mp = (SubjectClass) session.get(SubjectClass.class, rs.getInt("assId"));
				maped.add(mp);
			}

			return maped;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<SubjectClass> getSubClassMapForClass(int cid) {
		Session session = HibernateUtility.getSession();

		dbOps db = new dbOps();
		Connection con = db.getConnection();
		SubjectClass mp;
		List<SubjectClass> maped = new ArrayList<SubjectClass>();
		try {
			PreparedStatement query = con
					.prepareStatement("select assId from subjects_class where CLASS_ID=" + cid );

			ResultSet rs = query.executeQuery();
			while (rs.next()) {
				mp = (SubjectClass) session.get(SubjectClass.class, rs.getInt("assId"));
				maped.add(mp);
			}

			return maped;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
