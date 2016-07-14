package Controller;
import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;
import Persistentie.AccountDao;
import Persistentie.BewonerDao;


public class Registerservlet extends HttpServlet {
	ServletConfig config;
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config)throws ServletException {
		this.config = config;
		super.init(config);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	try{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String passwordcheck = req.getParameter("passwordcheck");
		String kn = req.getParameter("kamernummer");
		int kamernummer = 0;
		kamernummer = Integer.parseInt(kn);
		BewonerDao bD;
		Bewoner b = null;
		if(kamernummer >= 353 && kamernummer <= 366){
			try {
				bD = new BewonerDao();
				b = bD.getBewoner(kamernummer);
			
				if(		"".equals(email) || "email".equals(email) ||
						"".equals(password) || "wachtwoord".equals(password) ||
						kamernummer == 0 || b == null ||
						!password.equals(passwordcheck)
						){
					req.setAttribute("fout", "fout");
					req.getRequestDispatcher("/register.jsp").forward(req, resp);
				}
				else{
		
					if(!b.getClaim()){
						AccountDao aD = new AccountDao();
						aD.createAccount(email, password, b.getId());
						bD.updateBewoner(b.getId(), b.getVoorNaam(), b.getAchterNaam(), b.getKamerNummer(), true);
						aD.close();
						bD.close();
						req.getRequestDispatcher("/login.jsp").forward(req, resp);
					}
					else{
						req.setAttribute("fout", "fout");
						req.getRequestDispatcher("/register.jsp").forward(req, resp);
					}
				}
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else{
			req.setAttribute("fout", "fout");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
	}catch(NumberFormatException e) { 
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    } catch(NullPointerException e) {
    	req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
		
	}
}
