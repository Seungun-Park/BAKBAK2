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
import com.bakbak2.model.InsertAction;
import java.io.PrintWriter;

@WebServlet("/insert.do")
public class InsertDispatch extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public InsertDispatch(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		InsertAction insert = new InsertAction(session);
		boolean check = insert.insertPost(request.getParameter("title"), request.getParameter("content"));

		if(check){
			response.sendRedirect("board?bo="+(String)session.getAttribute("bo"));
		}else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시글 등록 실패');location.href='write_board.jsp';</script>");
			out.flush();
		}
	}
}
