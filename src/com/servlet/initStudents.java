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

import com.datahandler.ClassHandler;
import com.datahandler.StudentHandler;
import com.entity.ClassRoom;
import com.entity.Student;

@WebServlet("/initStudents")
public class initStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public initStudents() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ClassHandler ch = new ClassHandler();
		StudentHandler sh = new StudentHandler();
		
		String act= (String) session.getAttribute("action");
		
		if(act.equals("save")) {
			request.setAttribute("stud_Name", "");
			request.setAttribute("stud_Age", "");
		}
		
		try {

			List<ClassRoom> listCatagory = ch.getClassList();
			request.setAttribute("listCategory", listCatagory);
			
			List<Student> listStudent=sh.getAllStuds();
			request.setAttribute("studentsList", listStudent);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("Student.jsp");
			dispatcher.include(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
