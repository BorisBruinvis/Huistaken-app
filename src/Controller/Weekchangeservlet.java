package Controller;
import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.*;


public class Weekchangeservlet extends HttpServlet {
	ServletConfig config;
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config)throws ServletException {
		this.config = config;
		super.init(config);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int Weeknr = Integer.parseInt(req.getParameter("change"));
		
		if(Weeknr == 15 || Weeknr == 0){
			req.getRequestDispatcher("welcome.jsp").forward(req, resp);
		}
		else{
			try {
				session.setAttribute("week", new Week(Weeknr));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.getRequestDispatcher("welcome.jsp").forward(req, resp);
		}
		
	}
}
	
	