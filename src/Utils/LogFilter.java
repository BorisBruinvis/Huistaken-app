package Utils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogFilter implements Filter  {
   
	public void  init(FilterConfig config) throws ServletException{
		  String testParam = config.getInitParameter("test-param"); 
	      System.out.println("Test Param: " + testParam); 
   }
	
   public void  doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws java.io.IOException, ServletException {
	   System.out.println("Filter do!");
	   HttpServletRequest request = (HttpServletRequest) req;
       HttpServletResponse response = (HttpServletResponse) resp;
       HttpSession session = request.getSession();
       
       if(session.getAttribute("loggedinUser") != null){
    	   System.out.println("if");
    	   chain.doFilter(request, response);
       }else{System.out.println("out state");
    	   request.getRequestDispatcher("/index.jsp").forward(request, response);}
  		}

   public void destroy(){
	   
   }
}