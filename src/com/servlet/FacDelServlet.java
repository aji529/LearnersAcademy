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

@WebServlet("/FacDelServlet")
public class FacDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public FacDelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		
		String t_id = (String) request.getParameter("id");
		System.out.println(t_id);
		
		int tid=0;
		try {
			tid = Integer.parseInt(t_id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		TeacherHandler th = new TeacherHandler();
		boolean success = th.deleteTeacher(tid);
		
		if(success) {
			out.println("<div align = 'center' style = \"margin:10px; color:green ;\">Teacher deleted...</div>");
		}else {
			out.println("<div align = 'center' style = \"margin:10px; color:red ;\">Teacher could not be be deleted!! </div>");
		}
		
		session.setAttribute("action", "save");
		String act = (String) session.getAttribute("action");
		if (act.equals("save")) {
			request.setAttribute("teacher_Name", "");
		}
		
		SubClassHandler sch = new SubClassHandler();
		ClassHandler ch = new ClassHandler();
		SubjectHandler sh = new SubjectHandler();
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
