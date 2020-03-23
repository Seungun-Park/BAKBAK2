/*박박이*/

package com.bakbak2.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import com.bakbak2.model.LoginAction;
import java.io.PrintWriter;

@WebServlet("/login.do")
public class LoginDispatch extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public LoginDispatch(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		if(session.getAttribute("isLogined")!=null && (Boolean)session.getAttribute("isLogined")){
			session.setAttribute("isLogined",false);
			session.setAttribute("ID",null);
			session.setAttribute("NICK",null);
			response.sendRedirect((String)session.getAttribute("url"));
			return;
		} 	

		LoginAction login = new LoginAction(request.getParameter("id"), request.getParameter("pw"));
		boolean check = login.tryLogin();	

		if(check){ 
			session.setAttribute("isLogined", true);
			session.setAttribute("ID", login.getIdx());
			session.setAttribute("NICK",login.getNick());

			response.sendRedirect((String)session.getAttribute("url"));
		}
		else{
		session.setAttribute("isLogined", false);
		session.setAttribute("ID", null);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('로그인 실패');location.href='" + (String)session.getAttribute("url") + "';</script>");	
		out.flush();
		}
	}
}
