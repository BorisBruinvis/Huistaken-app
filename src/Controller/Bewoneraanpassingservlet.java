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


public class Bewoneraanpassingservlet extends HttpServlet {
	ServletConfig config;
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config)throws ServletException {
		this.config = config;
		super.init(config);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String voornaam = req.getParameter("voornaam");
		String achternaam = req.getParameter("achternaam");
		String email = req.getParameter("email");
		String kN = req.getParameter("kamernummer");
		int kamernummer = Integer.parseInt(kN);
		HttpSession session = req.getSession();
		Bewoner b = (Bewoner) session.getAttribute("loggedinUser");
		BewonerDao bD = null;
		if(!"".equals(voornaam) || !"voornaam".equals(voornaam) ||
				!"".equals(achternaam) || !"achternaam".equals(achternaam) ||
				!"".equals(email) || !"email".equals(email) ||
				kamernummer >= 353 || kamernummer <= 366
				){
			try {
				bD = new BewonerDao();
				bD.updateBewoner(b.getId(), voornaam, achternaam, kamernummer, b.getClaim());
				AccountDao aD = new AccountDao();
				aD.updateAccount(email, b.getId());
				Bewoner bnieuw = bD.getBewoner(kamernummer);
				session.setAttribute("loggedinUser", bnieuw);
				bD.close();
				aD.close();
				req.getRequestDispatcher("account.jsp").forward(req, resp);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{
			req.setAttribute("fout", "fout");
			req.getRequestDispatcher("account.jsp").forward(req, resp);
		}

		
	}
}