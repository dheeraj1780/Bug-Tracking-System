package com.bts.signin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/emailtoadmin")
public class emailtoadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		HttpSession session = request.getSession(false);
        
		
        if (session != null && session.getAttribute("isAdmin") != null
                && (boolean) session.getAttribute("isAdmin")) {
        	
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/users");
            dispatcher.forward(request, response);
            // Admin is logged in, show contents or perform necessary operations
            
        } else {
            // Admin is not logged in, redirect to the login page
            response.sendRedirect("login.jsp");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
