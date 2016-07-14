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


public class Verwijderaccountservlet extends HttpServlet {
	ServletConfig config;
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config)throws ServletException {
		this.config = config;
		super.init(config);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		
		try {
			BewonerDao bD = new BewonerDao();
			AccountDao aD = new AccountDao();
			Bewoner b = (Bewoner) session.getAttribute("loggedinUser");
			Account a = b.getAccount();
			System.out.println(a.getWachtwoord().toUpperCase().equals(password.toUpperCase()) && a.getEmail().toUpperCase().equals(email.toUpperCase()));
			if(a.getWachtwoord().toUpperCase().equals(password.toUpperCase()) && a.getEmail().toUpperCase().equals(email.toUpperCase())){
				bD.updateBewoner(b.getId(), b.getVoorNaam(), b.getAchterNaam(), b.getKamerNummer(), false);
				aD.removeAccount(a.getBewonerId());
			}
			else{System.out.println("lukt niet if faalt");}
			bD.close();
			aD.close();
			session.removeAttribute("loggedinUser");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}
}