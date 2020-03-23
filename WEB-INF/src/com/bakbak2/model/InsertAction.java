/*
 * 박박이
 * 게시글 작성
 */

package com.bakbak2.model;

import com.bakbak2.model.SQLAction;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class InsertAction{
	Board post = null;
	HttpSession session = null;
	ResultSet rs = null;
	String board = null;

	public InsertAction(HttpSession session){
		this.session = session;
		this.board = (String)session.getAttribute("bo");
	}

	public boolean insertPost(String title, String content){
		SQLAction sql = new SQLAction();
		rs = sql.execute("select * from user_tbl where id = " + (Integer)session.getAttribute("ID"));
		int user_idx = 0;
		String nick = null;

		try{
			rs.next();
			user_idx = rs.getInt("id");
			nick = rs.getString("nickname");
		}catch(Exception e){
			System.out.println("fail load user data : " + e.getMessage());
		}
	
		if(board.equals("notice")){
			ResultSet admin = sql.execute("select * from admin_list");
			try{
				boolean check = true;
				while(admin.next()){
					if(admin.getInt("ref") == user_idx){
						check = false;
						break;
					}
				}
				if(check) return false;
			}catch(Exception e){
				System.out.println("fail load admin list : " + e.getMessage());
				return false;
			}
		}

	
		SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String date = frmt.format(time);
		
		return sql.insertPost(board, title, date, content, user_idx, nick);
	}
}
