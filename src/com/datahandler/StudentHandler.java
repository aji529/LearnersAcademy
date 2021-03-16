package com.datahandler;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.ClassRoom;
import com.entity.Student;
import com.utility.HibernateUtility;

public class StudentHandler {


	public boolean addStudent(String studName, int studAge) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		if (studAge == 0) {
			return false;
		}
		try {
			Student stud = new Student(studName, studAge);
			session.save(stud);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean addStudent(String studName, int studAge, int studCls) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		if (studAge == 0) {
			return false;
		}
		try {
			ClassRoom studCl =(ClassRoom)session.get(ClassRoom.class, studCls);
			Student stud = new Student(studName, studAge, studCl);
			session.save(stud);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public  List<Student> getAllStuds(){
		Session session = HibernateUtility.getSession();
		List<Student> studList = new ArrayList<Student>();
		try {
			Query query = session.createQuery("select studId from Student order by studId");
		

			@SuppressWarnings("unchecked")
			List<Integer> rdList = query.list();
			for (int id : rdList) {
				Student students = (Student) session.get(Student.class, id);
				studList.add(students);
			}
		} catch (Exception e) {
			System.out.println("List of unassigned students could not be retreived ");
			e.printStackTrace();
		}

		return studList;
	}

	public boolean updateStudent(int studId, String studName, int studAge) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		
		if (studAge == 0) {
			return false;
		}
		try {
			Student stud = (Student)session.get(Student.class, studId);
			stud.setStudName(studName);
			stud.setStudAge(studAge);
			
			Query query = session.createQuery("update Student set CLASS_ID = null where studId =:Id");
			query.setParameter("Id", studId);
			query.executeUpdate();
			
			session.update(stud);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateStudent(int studId, String studName, int studAge, int studCls) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		
		if (studAge == 0) {
			return false;
		}
		try {
			Student stud = (Student)session.get(Student.class, studId);
			stud.setStudName(studName);
			stud.setStudAge(studAge);
			
			ClassRoom  studCl = (ClassRoom) session.get(ClassRoom.class, studCls);
			stud.setstudCl(studCl);
			
			session.update(stud);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	
	public boolean deleteStud(int studId) {
		try {
			Session session = HibernateUtility.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete from Student where studId =:studId");
			query.setParameter("studId", studId);
			query.executeUpdate();
			tx.commit();
			return true;
		}
		catch(Exception e) {
			System.err.println("Exception in StudentHandler.deleteStud method....");
			e.printStackTrace();
			return false;
		}
		
	}

}
