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
import com.datahandler.SubjectHandler;
import com.entity.ClassRoom;
import com.entity.Subjects;

@WebServlet("/SubjectServlet")
public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubjectServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();
		String subName = request.getParameter("sub_Name");
		
		
		String[] subCl = null;
		try {
			subCl = request.getParameterValues("sub_Class");
		} catch (NullPointerException e) {
			System.out.println("No Class was selected");
		}
		
		
		String act = (String) session.getAttribute("action");

		SubjectHandler handler = new SubjectHandler();
		RequestDispatcher rd = request.getRequestDispatcher("Subject.jsp");

		if (subName.isEmpty()) {
			out.println("<div align = 'center' style = \"margin:10px; color:red ;\">Name is Mandatory..!! </div>");
			subName = null;
		}

		String message = null;
		boolean success = false;

		if (act.equals("save")) {

			if (subCl == null) {
				if (success = handler.addSubject(subName))
					message = "Student Added successfuly..!!";
				else
					message = "Student Addition Failed..!!";
			} else {

				if (success = handler.addSubject(subName, subCl))
					message = "Student Added successfuly..!!";
				else
					message = "Student Addition Failed..!!";
			}

		} else if (act.equals("update")) {

			int subId = (int) session.getAttribute("sid");
			System.out.println("Attribute sid " + subId);

			if (subCl == null) {
				if (success = handler.updateSubject(subId, subName))
					message = "Subject Updated successfuly..!!";
				else
					message = "Subject Update Failed..!!";
			} else {
				if (success = handler.updateSubject(subId, subName, subCl))
					message = "Subject Updated successfuly..!!";
				else
					message = "Subject Update Failed..!!";
			}

		}

		ClassHandler ch = new ClassHandler();
		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);

		List<Subjects> listSubs = handler.getAllSubs();
		request.setAttribute("subjectList", listSubs);

		session.setAttribute("action", "save");
		act = (String) session.getAttribute("action");
		if (act.equals("save")) {
			request.setAttribute("sub_Name", "");
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
