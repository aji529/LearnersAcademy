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

@WebServlet("/SubDelServlet")
public class SubDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubDelServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		String s_id = request.getParameter("id");
		int cid = 0;
		try {
			String c_id = request.getParameter("cid");
			cid = Integer.parseInt(c_id);
		} catch (Exception e) {
			System.out.println("Class not defined for the subject");
		}
		int subId = 0;
		try {
			subId = Integer.parseInt(s_id);
		} catch (Exception e) {
			out.println("Subject data could not be fetched");
		}

		SubjectHandler handler = new SubjectHandler();
		boolean success;
		String message= null;
		if (cid == 0) {
			success = handler.deleteSubjects(subId);
			message = "Subject deleted...";
		} else {
			success = handler.deleteSubjects(subId, cid);
			message ="Subject deleted from the class";
		}

		if (success) {
			out.println("<div align = 'center' style = \"margin:10px; color:green ;\">"+ message +"</div>");
		} else if(!success){
			out.println(
					"<div align = 'center' style = \"margin:10px; color:red ;\">Subject could not be be deleted!! </div>");
		}

		session.setAttribute("action", "save");
		String act = (String) session.getAttribute("action");
		if (act.equals("save")) {
			request.setAttribute("sub_Name", "");
		}

		ClassHandler ch = new ClassHandler();

		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		List<Subjects> listSubjects = handler.getAllSubs();
		request.setAttribute("subjectList", listSubjects);
		session.setAttribute("action", "save");

		RequestDispatcher rd = request.getRequestDispatcher("Subject.jsp");
		rd.include(request, response);
	}

}
