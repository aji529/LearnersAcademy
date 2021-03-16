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
import com.datahandler.SubjectHandler;
import com.entity.ClassRoom;
import com.entity.Subjects;

@WebServlet("/initSubjects")
public class initSubjects extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public initSubjects() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ClassHandler ch = new ClassHandler();
		SubjectHandler sh = new SubjectHandler();
		
		String act= (String) session.getAttribute("action");
		
		if(act.equals("save")) {
			request.setAttribute("sub_Name", "");
		}

		try {

			List<ClassRoom> listCatagory = ch.getClassList();
			request.setAttribute("listCategory", listCatagory);
			
			List<Subjects> listSubs=sh.getAllSubs();
			request.setAttribute("subjectList", listSubs);

			RequestDispatcher dispatcher = request.getRequestDispatcher("Subject.jsp");
			dispatcher.include(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
