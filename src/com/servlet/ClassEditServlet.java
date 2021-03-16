package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.datahandler.ClassHandler;
import com.entity.ClassRoom;
import com.utility.HibernateUtility;

@WebServlet("/ClassEditServlet")
public class ClassEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClassEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String c_id = request.getParameter("id");

		int cid = 0;
		System.out.println(c_id);

		cid = Integer.parseInt(c_id);
		Session sess = HibernateUtility.getSession();
		ClassRoom cls = (ClassRoom) sess.get(ClassRoom.class, cid);
		
		String name = cls.getClassName();

		ClassHandler ch = new ClassHandler();

		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		
		session.setAttribute("cid", cid);
		request.setAttribute("class_Name",name);
		request.setAttribute("cid", cid);
		session.setAttribute("action", "update");
	
		RequestDispatcher rd = request.getRequestDispatcher("Class.jsp");
		rd.include(request, response);

	}

}
