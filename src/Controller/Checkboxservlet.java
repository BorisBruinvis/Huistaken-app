package Controller;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Week;
import Persistentie.HuistaakuitvoeringDao;

public class Checkboxservlet extends HttpServlet {
	ServletConfig config;
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config)throws ServletException {
		this.config = config;
		super.init(config);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String stringid = req.getParameter("vink");
			if(stringid == null){
				req.getRequestDispatcher("welcome.jsp").forward(req, resp);
			}
			else{
				int id = Integer.parseInt(stringid);
				try {
					HuistaakuitvoeringDao huD = new HuistaakuitvoeringDao();
					huD.checkHuistaakuitvoering(id);
					huD.close();
					HttpSession session = req.getSession();
					session.setAttribute("week", new Week(((Week) session.getAttribute("week")).getWeekNr()));
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				req.getRequestDispatcher("welcome.jsp").forward(req, resp);
			}
	}
}