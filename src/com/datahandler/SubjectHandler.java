package com.datahandler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dbHandler.dbOps;
import com.entity.ClassRoom;
import com.entity.Subjects;
import com.utility.HibernateUtility;

public class SubjectHandler {

	public boolean addSubject(String subName, String[] subCl) {

		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			List<Integer> clsIds = new ArrayList<>();

			for (String sbCL : subCl) {
				clsIds.add(Integer.parseInt(sbCL));
			}

			List<ClassRoom> listCl = new ArrayList<ClassRoom>();

			for (int i : clsIds) {
				ClassRoom cl = (ClassRoom) session.get(ClassRoom.class, i);
				listCl.add(cl);
			}

			Subjects sub = new Subjects(subName, listCl);
			session.save(sub);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addSubject(String subName) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			Subjects sub = new Subjects(subName);
			session.save(sub);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not create Subject");
			return false;
		}
	}

	public List<Subjects> getAllSubs() {
		Session session = HibernateUtility.getSession();
		List<Subjects> subList = new ArrayList<Subjects>();

		try {
			Query query = session.createQuery("select subId from Subjects order by subId");

			@SuppressWarnings("unchecked")
			List<Integer> rdList = query.list();
			for (int id : rdList) {
				Subjects subject = (Subjects) session.get(Subjects.class, id);
				subList.add(subject);

			}
		} catch (Exception e) {
			System.out.println("List of unassigned students could not be retreived ");
			e.printStackTrace();
		}
		return subList;

	}

	public boolean updateSubject(int subId, String subName) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {

			dbOps db = new dbOps();
			db.deleteClassAssociation(subId);

			Subjects sub = (Subjects) session.get(Subjects.class, subId);
			sub.setSubName(subName);
			session.update(sub);
			tx.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not update Subject");
			return false;
		}
	}

	public boolean updateSubject(int subId, String subName, String[] subCl) {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();
		try {
			List<Integer> clsIds = new ArrayList<>();

			for (String sbCL : subCl) {
				clsIds.add(Integer.parseInt(sbCL));
			}

			List<ClassRoom> listCl = new ArrayList<ClassRoom>();

			for (int i : clsIds) {
				ClassRoom cl = (ClassRoom) session.get(ClassRoom.class, i);
				listCl.add(cl);
			}

			Subjects sub = (Subjects) session.get(Subjects.class, subId);
			sub.setSubName(subName);
			sub.setClassList(listCl);
			session.update(sub);
			tx.commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not update Subject");
			return false;
		}

	}

	public boolean deleteSubjects(int subId, int cid) {
		try {

			dbOps db = new dbOps();
			int i = db.deleteClassAssSpec(subId, cid);
			
			if (i == 0) {
				Session session = HibernateUtility.getSession();
				Transaction tx = session.beginTransaction();
				Query query = session.createQuery("delete from Subjects where subId =:subId");
				query.setInteger("subId", subId);
				query.executeUpdate();
				tx.commit();
				return true;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteSubjects(int subId) {
		try {
			
			Session session = HibernateUtility.getSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("delete from Subjects where subId =:subId");
			query.setInteger("subId", subId);
			query.executeUpdate();
			tx.commit();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
