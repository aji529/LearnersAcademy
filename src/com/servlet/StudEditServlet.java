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

import org.hibernate.Session;

import com.datahandler.ClassHandler;
import com.datahandler.StudentHandler;
import com.entity.ClassRoom;
import com.entity.Student;
import com.utility.HibernateUtility;

@WebServlet("/StudEditServlet")
public class StudEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StudEditServlet() {
        super();
   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		PrintWriter out =response.getWriter();
		
		
		String s_id= request.getParameter("id");
		String c_id = request.getParameter("cid");
		
		int cid=0;
		System.out.println( s_id );
		
	
		int sid=0;
		
		
		try {
			sid=Integer.parseInt(s_id);
		}
		catch(Exception e) {
			out.println("Student data could not be fetched");
		}
		
		try {
			cid = Integer.parseInt(c_id);
		}
		catch(Exception e) {
			System.out.println("cid not available");
		}
	
		Session sess = HibernateUtility.getSession();
		Student stud = (Student) sess.get(Student.class, sid);
		
		
		String name = stud.getStudName();
		int age = stud.getStudAge();
		
		
		StudentHandler handler = new StudentHandler();
		ClassHandler ch = new ClassHandler();
		
		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		List<Student> listStudent = handler.getAllStuds();
		request.setAttribute("studentsList", listStudent);
		
		
		session.setAttribute("sid", sid);
		request.setAttribute("stud_Name",name);
		request.setAttribute("stud_Age", age);
		request.setAttribute("cid", cid);
		session.setAttribute("action", "update");
		
		RequestDispatcher rd = request.getRequestDispatcher("Student.jsp");
		rd.include(request, response);
		
	}

}
