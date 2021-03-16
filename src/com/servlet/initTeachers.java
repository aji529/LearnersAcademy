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
import com.datahandler.SubClassHandler;
import com.datahandler.SubjectHandler;
import com.datahandler.TeacherHandler;
import com.entity.ClassRoom;
import com.entity.SubjectClass;
import com.entity.Subjects;
import com.entity.Teacher;


@WebServlet("/initTeachers")
public class initTeachers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public initTeachers() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String act = (String) session.getAttribute("action");

		int tid = 0;
		
		TeacherHandler th = new TeacherHandler();
		SubClassHandler sch = new SubClassHandler();
		ClassHandler ch = new ClassHandler();
		SubjectHandler sh = new SubjectHandler();
		
		
		if (act.equals("save")) {
			request.setAttribute("teacher_Name", "");
		}
		
		
		try {
			
			List<Teacher> facList = th.getTeacherList();
			request.setAttribute("faclist", facList);
			
			List<SubjectClass> list = sch.getSubClassMapping(tid);
			request.setAttribute("listMapping", list);
			
			List<ClassRoom> listCatagory = ch.getClassList();
			request.setAttribute("listCategory", listCatagory);
			
			List<Subjects> listSub = sh.getAllSubs();
			request.setAttribute("listSubs", listSub);

			RequestDispatcher dispatcher = request.getRequestDispatcher("Teachers.jsp");
			dispatcher.include(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
