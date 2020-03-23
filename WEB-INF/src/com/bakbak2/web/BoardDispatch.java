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
import com.bakbak2.model.BoardAction;
import com.bakbak2.model.PostAction;
import com.bakbak2.model.Board;
import java.util.*;

@WebServlet("/board")
public class BoardDispatch extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public BoardDispatch(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String board_name = request.getParameter("bo");
		String url = request.getRequestURL() + "?" + request.getQueryString();
		session.setAttribute("url", url);
		RequestDispatcher dispatcher = null;

		if(request.getParameter("bo")==null){
			response.sendRedirect("board?bo=notice");
			return;
		}
		
		session.setAttribute("bo", board_name);
		if(session.getAttribute("ID")==null || session.getAttribute("NICK")==null)
				session.setAttribute("isLogined",false);
		
		BoardAction board = new BoardAction();
		PostAction post = new PostAction(board_name);

		List<String> board_list = board.loadBoardList();
		session.setAttribute("board_list", board_list);

		if(request.getParameter("id")==null){					
			List<Board> post_list = post.loadPostList();

			request.setAttribute("post_list", post_list);

			dispatcher = request.getRequestDispatcher("board.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else{
			int post_id = Integer.parseInt(request.getParameter("id"));

			Board p = post.loadPost(post_id);
			
			if(p == null){
				response.sendRedirect("board?bo="+board_name);
				return;
			}
			request.setAttribute("post", p);

			dispatcher = request.getRequestDispatcher("show_content.jsp");
			dispatcher.forward(request, response);
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
