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
import com.bakbak2.model.DeleteAction;
import java.io.PrintWriter;

@WebServlet("/delete.do")
public class DeleteDispatch extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public DeleteDispatch(){
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		DeleteAction del = new DeleteAction();
		int id = -1;

		if(session.getAttribute("isLogined")!=null && (Boolean)session.getAttribute("isLogined")){
			if(session.getAttribute("ID")==null){
				response.sendRedirect((String)session.getAttribute("url"));
			}else{
				id = (Integer)session.getAttribute("ID");
			}
		}else{
			response.sendRedirect((String)session.getAttribute("url"));
			return;
		}

		boolean check = del.doDel(request.getParameter("bo"), Integer.parseInt(request.getParameter("id")), id);

		if(check){
			response.sendRedirect("board?bo="+request.getParameter("bo"));
			return;
		}else{
			response.sendRedirect((String)session.getAttribute("url"));
			return;
		}
	}
}
