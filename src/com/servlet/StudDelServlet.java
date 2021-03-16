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


@WebServlet("/StudDelServlet")
public class StudDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public StudDelServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		
		String s_id = (String) request.getParameter("id");
		System.out.println(s_id);
		
		int studId=0;
		try {
			studId = Integer.parseInt(s_id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		StudentHandler sh = new StudentHandler();
		boolean success = sh.deleteStud(studId);
		
		if(success) {
			out.println("<div align = 'center' style = \"margin:10px; color:green ;\">Student deleted...</div>");
		}else {
			out.println("<div align = 'center' style = \"margin:10px; color:red ;\">Student could not be be deleted!! </div>");
		}
		
		session.setAttribute("action", "save");
		String act = (String) session.getAttribute("action");
		if(act.equals("save")) {
			request.setAttribute("stud_Name", "");
			request.setAttribute("stud_Age", "");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Student.jsp");
		ClassHandler ch = new ClassHandler();
		
		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		List<Student> listStudent = sh.getAllStuds();
		request.setAttribute("studentsList", listStudent);
		
		rd.include(request, response);
	}

}
