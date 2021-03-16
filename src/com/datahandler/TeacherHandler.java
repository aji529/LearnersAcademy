package com.datahandler;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dbHandler.dbOps;
import com.entity.SubjectClass;
import com.entity.Teacher;
import com.utility.HibernateUtility;

public class TeacherHandler {

	public boolean addTeacher(String facName) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Teacher stud = new Teacher(facName);
			session.save(stud);
			tx.commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean addTeacher(String facName, String[] subClsAll) {
		try {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		List<Integer> subClIds = new ArrayList<>();

		for (String subCl : subClsAll) {
			subClIds.add(Integer.parseInt(subCl));
		}
		
		List<SubjectClass> listCl = new ArrayList<SubjectClass>();
		
		for (int i : subClIds) {
			SubjectClass cl = (SubjectClass) session.get(SubjectClass.class, i);
			listCl.add(cl);
		}
		
		Teacher fac = new Teacher(facName);
		fac.setScPair(listCl);
		
		session.save(fac);
		tx.commit();
		
		return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean updateTeacher(int facId, String facName) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {

			dbOps db = new dbOps();
			db.deleteSubClassAssociation(facId);

			Teacher fac = (Teacher) session.get(Teacher.class, facId);
			fac.setTeachName(facName);
			session.update(fac);
			tx.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not update Subject");
			return false;
		}
	}

	public boolean updateTeacher(int facId, String facName, String[] subClsAll) {
		
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			dbOps db = new dbOps();
			db.deleteSubClassAssociation(facId);
			
			List<Integer> subClIds = new ArrayList<>();

			for (String subCl : subClsAll) {
				subClIds.add(Integer.parseInt(subCl));
			}
			
			List<SubjectClass> listCl = new ArrayList<SubjectClass>();
			
			for (int i : subClIds) {
				SubjectClass cl = (SubjectClass) session.get(SubjectClass.class, i);
				listCl.add(cl);
			}
			
			Teacher fac = (Teacher) session.get(Teacher.class, facId);
			fac.setScPair(listCl);
			
			session.update(fac);
			tx.commit();
			
			return true;
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
	}

	public List<Teacher> getTeacherList() {
		Session session = HibernateUtility.getSession();
		List<Teacher> facList = new ArrayList<Teacher>();
		try {
			Query query = session.createQuery("select teachId from Teacher order by teachId");
			
			@SuppressWarnings("unchecked")
			List<Integer> rdList = query.list();
			
			for (int id : rdList) {
				Teacher faculty = (Teacher) session.get(Teacher.class, id);
				facList.add(faculty);

			}
			
			System.out.println(facList.toString());
			
		} catch (Exception e) {
			System.out.println("List of teachers could not be retreived ");
			e.printStackTrace();
		}
		return facList;
	}

	public boolean deleteTeacher(int tid) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			
			dbOps db = new dbOps();
			db.deleteSubClassAssociation(tid);
			
			Query query =session.createQuery("delete from Teacher where teachId =:tid");
			query.setParameter("tid", tid);
			query.executeUpdate();
			
		
			tx.commit();
			
			return true;
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
	}

}
