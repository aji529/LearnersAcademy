package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datahandler.ClassHandler;
import com.datahandler.StudentHandler;
import com.entity.ClassRoom;
import com.entity.Student;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		String studName = request.getParameter("stud_Name");
		String studAgeAsString = request.getParameter("stud_Age");

		String act = (String) session.getAttribute("action");
		System.out.println("Attribute action = "+ act);

		int studCls = 0;
		try {
			studCls = Integer.parseInt(request.getParameter("stud_Class"));
		} catch (Exception e) {
			System.out.println("Class Id not read in the expected format");
		}

		int studAge = 0;

		StudentHandler handler = new StudentHandler();
		RequestDispatcher rd = request.getRequestDispatcher("Student.jsp");

		if (studName.isEmpty()) {
			out.println("<div align = 'center' style = \"margin:10px; color:red ;\">Name is Mandatory..!! </div>");
			studName = null;
		}
		if (studAgeAsString.isEmpty()) {
			out.println("<div align = 'center' style = \"margin:10px; color:red ;\">Age is Mandatory..!! </div>");

		}

		try {
			studAge = Integer.parseInt(studAgeAsString);
		} catch (Exception e) {
			out.println(
					"<div align = 'center' style = \"margin:10px; color:red ;\">You have entered invalid data..!! </div>");
		}
		
		
		
		String message = null;
		boolean success = false;
		
		
		
		if (act.equals("save")) {
			
			
			if (studCls == 0) {
				if (success = handler.addStudent(studName, studAge))
					message = "Student Added successfuly..!!";
				else
					message = "Student Addition Failed..!!";
			} else {
				
				if (success = handler.addStudent(studName, studAge,studCls))
					message = "Student Added successfuly..!!";
				else
					message = "Student Addition Failed..!!";
			}
			
			
		} else if (act.equals("update")) {
			
			
			int studId = (int) session.getAttribute("sid") ;
			System.out.println("Attribute sid "+ studId);
			
			if (studCls == 0) {
				if(success = handler.updateStudent(studId,studName, studAge))
					message="Student Updated successfuly..!!";
				else
					message="Student Update Failed..!!";
			} else {
				if(success = handler.updateStudent(studId,studName, studAge, studCls))
					message="Student Updated successfuly..!!";
				else
					message="Student Update Failed..!!";
			}
			
		}
		
		
		ClassHandler ch = new ClassHandler();
		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		List<Student> listStudent = handler.getAllStuds();
		request.setAttribute("studentsList", listStudent);
		
		
		session.setAttribute("action", "save");
		act = (String) session.getAttribute("action");
		if(act.equals("save")) {
			request.setAttribute("stud_Name", "");
			request.setAttribute("stud_Age", "");
		}
		
		if (success) {
			out.println("<div align = 'center' style = \"margin:10px; color:green;\">" + message + " </div>");
			rd.include(request, response);

		} else {
			out.println("<div align = 'center' style = \"margin:10px; color:red;\">" + message + "</div>");
			rd.include(request, response);
		}

		out.close();

	}

}
