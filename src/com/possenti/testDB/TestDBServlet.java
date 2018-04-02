package com.possenti.testDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TestDBServlet
 */
@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//setup connection variables 
		String user = "postgres";
		String pass = "root";		
		String jdcUrl = "jdbc:postgresql://localhost:5433/web_customer_tracker";
		String driver = "org.postgresql.Driver";
		
		//get connection to database
		try{
			PrintWriter out = response.getWriter();
			out.println("Connecting to database: " + jdcUrl);
			
			Class.forName(driver); 
			Connection myConn = DriverManager.getConnection(jdcUrl,user,pass);
			
			out.println("SUCCESS!!");
			
			myConn.close();
						
		}catch(Exception exc) {
			exc.printStackTrace();
			throw new ServletException(exc);
		}
		
		
		
	}

}
