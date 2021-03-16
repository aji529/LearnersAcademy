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

@WebServlet("/ClassDeleteServlet")
public class ClassDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ClassDeleteServlet() {
        super();
 
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String c_id = (String) request.getParameter("id");
		System.out.println(c_id);
		
		int cid=0;
		try {
			cid = Integer.parseInt(c_id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ClassHandler ch = new ClassHandler();
		boolean success = ch.deleteClass(cid);
		
		if(success) {
			out.println("<div align = 'center' style = \"margin:10px; color:green ;\">Class deleted...</div>");
		}else {
			out.println("<div align = 'center' style = \"margin:10px; color:red ;\">Class could not be be deleted!! </div>");
		}
		
		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		
		session.setAttribute("action", "save");
		String act = (String) session.getAttribute("action");
		if(act.equals("save")) {
			request.setAttribute("class_Name", "");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Class.jsp");
		dispatcher.include(request, response);
		
		
		
	}

}
