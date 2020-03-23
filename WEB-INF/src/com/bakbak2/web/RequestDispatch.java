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
import com.bakbak2.model.RequestAction;
import java.io.PrintWriter;

@WebServlet("/request.do")
public class RequestDispatch extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public RequestDispatch(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		RequestAction rqst = new RequestAction(request.getParameter("title"), request.getParameter("maincat_text"), request.getParameter("sub_cat"), request.getParameter("request_reason"), request.getParameter("board_introduce"));
		
		boolean check = rqst.tryRegister();
		
		if(check){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시판 등록 성공');location.href='board';</script>");
			out.flush();
		}else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시판 등록 실패');location.href='request_board.jsp';</script>");
			out.flush();
		}
	}
}
