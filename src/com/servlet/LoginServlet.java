package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.UserHandler;
import com.utility.HibernateUtility;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("uname");
		System.out.println("UserName" + name);
		String password = request.getParameter("upass");
		
		Session session= HibernateUtility.getSession();
		Transaction tx= session.beginTransaction();
		UserHandler admin = new UserHandler("admin", "major");
		try {
			session.save(admin);
			tx.commit();
		}
		catch(Exception e) {
			System.out.println("user already mapped");
		}
		
		
		UserHandler lh = new UserHandler();
		lh.setUsername(name);
		lh.setPassword(password);
		
		if (lh.LoginValid()) {
			HttpSession sess = request.getSession();
			sess.setAttribute("User_Name", name);
			sess.setAttribute("action", "save");
			response.sendRedirect("HomePage.jsp");
		

		} else {
			
			out.println("<div align = 'center' style = \"margin:10px; color:red ;\">You have entered incorrect credentials..!! </div>");
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.include(request, response);
            
		}
	}

}
