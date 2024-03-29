package info.chatforyou.main;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

public class AuthFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
		try{
			String target = ((HttpServletRequest)request).getRequestURI();

			HttpSession session = ((HttpServletRequest)request).getSession(false);

			if (session == null){
				/* まだ認証されていない */

				((HttpServletResponse)response).sendRedirect("/chatforyou/index.jsp");
			}else{
				Object loginCheck = session.getAttribute("login");
				if (loginCheck == null){
					/* まだ認証されていない */
					((HttpServletResponse)response).sendRedirect("/chatforyou/index.jsp");
				}else{
					//認証されていれば通す
					chain.doFilter(request, response);
				}
			}
		}catch (ServletException se){
	    	  
		}catch (IOException e){
	    	  
	      }
	}

	public void init(FilterConfig filterConfig) throws ServletException{
	}

	public void destroy(){
	}
}

