/*
 * 박박이
 * 게시판의 게시글 목록을 읽어오는 액션
 */

package com.bakbak2.model;

import com.bakbak2.model.SQLAction;
import com.bakbak2.model.Board;
import java.util.*;
import java.sql.ResultSet;

public class PostAction{
	SQLAction sql = null;
	String board = null;
	ResultSet rs = null;

	public PostAction(String board){
		sql = new SQLAction();
		this.board = board;
	}

	public List<Board> loadPostList(){
		rs = sql.execute("select * from " + board + " order by id desc");

		List<Board> list = new ArrayList<>();
		try{
			rs.beforeFirst();
			while(rs.next()){
				list.add(new Board(rs.getInt("id"), rs.getString("title"), rs.getString("date"), rs.getString("content"), rs.getString("nickname"), rs.getInt("user"), rs.getInt("cnt"), rs.getString("board")));
			}
		}catch(Exception e){
			System.out.println("fail to read post list : " + e.getMessage());
		}
		return list;
	}

	public Board loadPost(int id){
		rs = sql.execute("select * from " + board + " order by id");

		try{
			rs.beforeFirst();
			while(rs.next()){
				if(id == rs.getInt("id")){
					sql.cntUp(rs.getInt("id"), rs.getString("board"), rs.getInt("cnt")+1);
					return new Board(rs.getInt("id"), rs.getString("title"), rs.getString("date"), rs.getString("content"), rs.getString("nickname"), rs.getInt("user"), rs.getInt("cnt")+1, rs.getString("board"));
				}
			}
		}catch(Exception e){
			System.out.println("fail to read post : " + e.getMessage());
		}
		return null;
	}
}
