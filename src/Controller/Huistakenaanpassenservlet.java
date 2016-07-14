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
import Persistentie.HuistaakDao;


public class Huistakenaanpassenservlet extends HttpServlet {
	ServletConfig config;
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config)throws ServletException {
		this.config = config;
		super.init(config);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titel = req.getParameter("titel");
		String omschrijving = req.getParameter("omschrijving");
		String stringId = req.getParameter("titelid");
		int id = Integer.parseInt(stringId);
		try {
			if(id >= 1 || id <= 14 ||
				!"".equals(titel) || !"titel".equals(titel) ||
				!"".equals(omschrijving) || !"omschrijving".equals(omschrijving)
					){
				HuistaakDao hD = new HuistaakDao();
				hD.changeHuistaak(id, titel, omschrijving);
				HttpSession session = req.getSession();
	
				session.setAttribute("week", new Week(((Week) session.getAttribute("week")).getWeekNr()));
				req.getRequestDispatcher("huistaken.jsp").forward(req, resp);
				hD.close();
			}
			else{
				req.setAttribute("fout", "fout");
				req.getRequestDispatcher("huistaken.jsp").forward(req, resp);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	
	
}