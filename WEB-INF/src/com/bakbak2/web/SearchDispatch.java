/*¹Ú¹ÚÀÌ*/

package com.bakbak2.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import com.bakbak2.model.SearchAction;
import com.bakbak2.model.Board;
import java.util.*;

@WebServlet("/search.do")
public class SearchDispatch extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public SearchDispatch(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String url = request.getRequestURL() + "?" + request.getQueryString();
		session.setAttribute("url", url);

		String search = request.getParameter("search");

		SearchAction srch = new SearchAction();
		List<Board> result = srch.doSearch(search);

		session.setAttribute("bo", "search");
		request.setAttribute("bo", "search");
		request.setAttribute("post_list",result);

		RequestDispatcher dispatcher = request.getRequestDispatcher("board.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
