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
import com.bakbak2.model.RegisterAction;
import java.io.PrintWriter;

@WebServlet("/register.do")
public class RegisterDispatch extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public RegisterDispatch(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		RegisterAction reg = new RegisterAction(request.getParameter("id"), request.getParameter("pw"), request.getParameter("nickname"));
		
		boolean check = reg.tryRegister();
		
		if(check){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입 성공');location.href='board';</script>");
			out.flush();
		}else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입 실패');location.href='register.jsp';</script>");
			out.flush();
		}
	}
}
