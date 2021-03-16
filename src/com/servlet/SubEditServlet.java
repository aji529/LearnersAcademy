package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.datahandler.SubjectHandler;
import com.entity.ClassRoom;
import com.entity.Subjects;
import com.utility.HibernateUtility;

@WebServlet("/SubEditServlet")
public class SubEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		String s_id = request.getParameter("id");

		System.out.println(s_id);

		int sid = 0;

		try {
			sid = Integer.parseInt(s_id);
		} catch (Exception e) {
			out.println("Subject data could not be fetched");
		}

		Session sess = HibernateUtility.getSession();
		Subjects sub = (Subjects) sess.get(Subjects.class, sid);

		String name = sub.getSubName();
		List<ClassRoom> cr = new ArrayList<ClassRoom>();
		cr = sub.getClassList();
		
		List<Integer> cid = new ArrayList<>();
		try {
			for (ClassRoom c : cr)
				cid.add(c.getClassId());
		} catch (Exception e) {
			cid = null;
		}
		
		
		SubjectHandler handler = new SubjectHandler();
		ClassHandler ch = new ClassHandler();

		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		
		List<Subjects> listSubjects = handler.getAllSubs();
		request.setAttribute("subjectList", listSubjects);

		session.setAttribute("sid", sid);
		request.setAttribute("sub_Name", name);
		request.setAttribute("cid", cid);
		session.setAttribute("action", "update");

		RequestDispatcher rd = request.getRequestDispatcher("Subject.jsp");
		rd.include(request, response);
	}

}
