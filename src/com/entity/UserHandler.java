package com.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.utility.HibernateUtility;

@Entity
@Table(name = "users_list")
public class UserHandler {

	@Id
	@Column(name = "idUSERS")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserId;

	
	@Column(name = "user_Name", unique = true)
	private String userName;

	@Column(name = "user_Pass")
	private String password;

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserHandler(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public UserHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean LoginValid() {
		Session session = HibernateUtility.getSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("from UserHandler where user_Name = :usn");
		query.setString("usn", "admin");
		UserHandler refUser = (UserHandler) query.uniqueResult();
		try {
			if (refUser != null) {
				if (refUser.getUsername().equals(this.getUsername())
						&& refUser.getPassword().equals(this.getPassword())) {
					return true;
				}

				tx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;

	}

}
