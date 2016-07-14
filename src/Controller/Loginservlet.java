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
import Persistentie.AccountDao;
import Persistentie.BewonerDao;


public class Loginservlet extends HttpServlet {
	ServletConfig config;
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config)throws ServletException {
		this.config = config;
		super.init(config);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			BewonerDao bD = new BewonerDao();
			AccountDao aD = new AccountDao();
			
			Account a = aD.getAccount(email);
			Bewoner b = bD.getBewonerById(a.getBewonerId());
			
			Week wk = new Week(1);
			aD.close();
			bD.close();
			
			if(a.getEmail() == null || !a.getWachtwoord().equals(password)){
				req.setAttribute("fout", "fout");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
			else{
				HttpSession session = req.getSession();
				session.setAttribute("week", wk);
				session.setAttribute("loggedinUser", b);

				req.getRequestDispatcher("/huistakenapp/welcome.jsp").forward(req, resp);
			}
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}