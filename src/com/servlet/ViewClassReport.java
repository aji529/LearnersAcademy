package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.datahandler.StudentHandler;
import com.datahandler.SubClassHandler;
import com.datahandler.SubjectHandler;
import com.datahandler.TeacherHandler;

import com.entity.Student;
import com.entity.SubjectClass;
import com.entity.Subjects;
import com.entity.Teacher;


@WebServlet("/ViewClassReport")
public class ViewClassReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewClassReport() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String c_id = (String) request.getParameter("id");
		System.out.println(c_id);
		
		int cid=0;
		try {
			cid = Integer.parseInt(c_id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		StudentHandler st = new StudentHandler();
		List<Student> studList=st.getAllStuds();
		
		TeacherHandler th = new TeacherHandler();
		List<Teacher> teacherList = th.getTeacherList();
		
		SubjectHandler sh= new SubjectHandler();
		List<Subjects> subList = sh.getAllSubs();
		
		SubClassHandler sch = new SubClassHandler();
		List<SubjectClass> subClMap = sch.getSubClassMapForClass(cid);  
		
		
		request.setAttribute("clsId", cid);
		request.setAttribute("studentList", studList);
		request.setAttribute("teacherList", teacherList);
		request.setAttribute("subjectList", subList);
		request.setAttribute("mappingList", subClMap);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ClassReports.jsp");
		dispatcher.include(request, response);
		
	}

}
