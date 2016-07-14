package Controller;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Logoutservlet extends HttpServlet {
	ServletConfig config;
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config)throws ServletException {
		this.config = config;
		super.init(config);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				HttpSession session = req.getSession();
				session.removeAttribute("loggedinUser");
				session.removeAttribute("week");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
	
	