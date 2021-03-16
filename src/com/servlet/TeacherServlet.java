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
import com.datahandler.SubClassHandler;
import com.datahandler.SubjectHandler;
import com.datahandler.TeacherHandler;
import com.entity.ClassRoom;
import com.entity.SubjectClass;
import com.entity.Subjects;
import com.entity.Teacher;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeacherServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();
		String facName = request.getParameter("fac_Name");
	
		String[] subClsAll = null;
		try {
			subClsAll = request.getParameterValues("sub_Class_alloc");
		} catch (NullPointerException e) {
			System.out.println("No Class was selected");
		}
		
		
		String act = (String) session.getAttribute("action");
		System.out.println("Attribute action = " + act);


		TeacherHandler handler = new TeacherHandler();
		RequestDispatcher rd = request.getRequestDispatcher("Teachers.jsp");

		if (facName.isEmpty()) {
			out.println("<div align = 'center' style = \"margin:10px; color:red ;\">Name is Mandatory..!! </div>");
			facName = null;
		}

		String message = null;
		boolean success = false;

		if (act.equals("save")) {

			if (subClsAll == null) {
				if (success = handler.addTeacher(facName))
					message = "Faculty Added successfuly..!!";
				else
					message = "Faculty Addition Failed..!!";
			} else {

				if (success = handler.addTeacher(facName,subClsAll))
					message = "Faculty Added successfuly..!!";
				else
					message = "Faculty Addition Failed..!!";
			}

		} else if (act.equals("update")) {

			int facId = (int) session.getAttribute("tid");
			System.out.println("Attribute fid " + facId);

			if (subClsAll == null) {
				if (success = handler.updateTeacher(facId, facName))
					message = "Faculty Updated successfuly..!!";
				else
					message = "Faculty Update Failed..!!";
			} else {
				if (success = handler.updateTeacher(facId, facName,subClsAll))
					message = "Faculty Updated successfuly..!!";
				else
					message = "Faculty Update Failed..!!";
			}

		}
		
		session.setAttribute("action", "save");
		act = (String) session.getAttribute("action");
		
		if(act.equals("save")) {
			request.setAttribute("teacher_Name", "");
		}
		
		TeacherHandler th = new TeacherHandler();
		SubClassHandler sch = new SubClassHandler();
		ClassHandler ch = new ClassHandler();
		SubjectHandler sh = new SubjectHandler();
		
		List<Teacher> facList = th.getTeacherList();
		request.setAttribute("faclist", facList);
		
		List<SubjectClass> list = sch.getSubClassMapping(0);
		request.setAttribute("listMapping", list);
		
		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		
		List<Subjects> listSub = sh.getAllSubs();
		request.setAttribute("listSubs", listSub);
		
		
		
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
