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
import com.entity.ClassRoom;

@WebServlet("/ClassServlet")
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClassServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();
		String className = request.getParameter("class_Name");

		String act = (String) session.getAttribute("action");
		System.out.println("Attribute action = " + act);

		ClassHandler handler = new ClassHandler();
		RequestDispatcher rd = request.getRequestDispatcher("Class.jsp");

		if (className.isEmpty()) {
			out.println("<div align = 'center' style = \"margin:10px; color:red ;\">Name is Mandatory..!! </div>");
			className = null;
		}

		String message = null;
		boolean success = false;

		if (act.equals("save")) {
			
			if(success = handler.addClass(className))
				message="Class saved successfuly..!!";
			
		} else if (act.equals("update")) {
			
			int classId = (int) session.getAttribute("cid");
			System.out.println("Attribute cid " + classId);
			if(success = handler.updateClass(className,classId))
				message="Class Updated successfuly..!!";
			
		}
		
		ClassHandler ch = new ClassHandler();
		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		
		
		session.setAttribute("action", "save");
		act = (String) session.getAttribute("action");
		if(act.equals("save")) {
			request.setAttribute("class_Name", "");
		}
		
		
		
		if (success) {
			out.println("<div align = 'center' style = \"margin:10px; color:green;\">" + message + " </div>");
			rd.include(request, response);

		} else {
			out.println("<div align = 'center' style = \"margin:10px; color:red;\"> Failed to make the changes...</div>");
			rd.include(request, response);
		}
	}

}
