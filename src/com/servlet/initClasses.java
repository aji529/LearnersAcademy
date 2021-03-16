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
import com.entity.ClassRoom;

@WebServlet("/initClasses")
public class initClasses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public initClasses() {
        super();
      
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ClassHandler ch = new ClassHandler();
		
		String act= (String) session.getAttribute("action");
		
		if(act.equals("save")) {
			request.setAttribute("class_Name", "");
			
		}
		
		List<ClassRoom> listCatagory = ch.getClassList();
		request.setAttribute("listCategory", listCatagory);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Class.jsp");
		dispatcher.include(request, response);
	}

}
