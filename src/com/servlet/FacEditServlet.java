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

import org.hibernate.Session;

import com.datahandler.ClassHandler;
import com.datahandler.SubClassHandler;
import com.datahandler.SubjectHandler;
import com.datahandler.TeacherHandler;
import com.entity.ClassRoom;
import com.entity.SubjectClass;
import com.entity.Subjects;
import com.entity.Teacher;
import com.utility.HibernateUtility;

@WebServlet("/FacEditServlet")
public class FacEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public FacEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String t_id = request.getParameter("id");
		
		int tid = 0;
		System.out.println(t_id);
		
		tid = Integer.parseInt(t_id);
		
		Session sess = HibernateUtility.getSession();
		
		TeacherHandler th= new TeacherHandler();
		SubClassHandler sch = new SubClassHandler();
		ClassHandler ch = new ClassHandler();
		SubjectHandler sh = new SubjectHandler();
		
		Teacher fac= (Teacher) sess.get(Teacher.class, tid);
		String fac_Name = fac.getTeachName();
		
		List<Teacher> facList = th.getTeacherList();
		request.setAttribute("faclist", facList);
		
		List<SubjectClass> list = sch.getSubClassMapping(tid);
		request.setAttribute("listMapping", list);
		
		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		
		List<Subjects> listSub = sh.getAllSubs();
		request.setAttribute("listSubs", listSub);
		
		session.setAttribute("tid", tid);
		request.setAttribute("teacher_Name",fac_Name);
		request.setAttribute("tid", tid);
		session.setAttribute("action", "update");
		
		RequestDispatcher rd = request.getRequestDispatcher("Teachers.jsp");
		rd.include(request, response);
		
	}

}
