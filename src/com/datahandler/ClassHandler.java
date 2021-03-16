package com.datahandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dbHandler.dbOps;
import com.entity.ClassRoom;
import com.utility.HibernateUtility;

public class ClassHandler {

	public List<ClassRoom> getClassList() {

		Session session = HibernateUtility.getSession();
		List<ClassRoom> classList = new ArrayList<ClassRoom>();
		try {
			Query query = session.createQuery("select classId from ClassRoom order by classId");

			@SuppressWarnings("unchecked")
			List<Integer> rdList = query.list();
			for (int id : rdList) {
				ClassRoom cls = (ClassRoom) session.get(ClassRoom.class, id);
				classList.add(cls);
			}
		} catch (Exception e) {
			System.out.println("List of unassigned students could not be retreived ");
			e.printStackTrace();
		}

		return classList;
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/l_academy", "Scott", "tiger");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public boolean addClass(String className) {
		
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			ClassRoom cr = new ClassRoom(className);
			session.save(cr);
			tx.commit();
			return true;
		}catch(Exception e) {
			System.err.println("Could not add class");
			return false;
		}
	}


	public boolean updateClass(String className,int cid) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			ClassRoom cr = (ClassRoom) session.get(ClassRoom.class, cid);
			cr.setClassName(className);
			session.update(cr);
			tx.commit();
			return true;
		}catch(Exception e) {
			System.err.println("Could not add class");
			return false;
		}
	
	}

	public boolean deleteClass(int cid) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			
			dbOps db = new dbOps();
			db.deleteClassDependencies(cid);
			
			Query query =session.createQuery("delete from ClassRoom where classId =:cid");
			query.setParameter("cid", cid);
			query.executeUpdate();
			tx.commit();
			return true;
		}catch(Exception e) {
			System.err.println("Could not add class");
			return false;
		}
	}


}
