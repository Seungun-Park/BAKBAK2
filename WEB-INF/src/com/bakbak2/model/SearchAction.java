package com.bakbak2.model;

import com.bakbak2.model.SQLAction;
import com.bakbak2.model.Board;
import java.sql.ResultSet;
import java.util.*;

public class SearchAction{
	SQLAction sql = null;
	ResultSet rs = null;
	public SearchAction(){
		sql = new SQLAction();
	}

	public List<Board> doSearch(String search){
		List<Board> result = new ArrayList<Board>();
		List<String> bo = new ArrayList<String>();
		
		rs = sql.execute("select * from board_list");

		try{
			while(rs.next()){
				bo.add(rs.getString("name"));
			}
			for(String e : bo){
				rs = null;
				rs = sql.execute("select * from " + e + " where title LIKE '%" + search +"%' OR content LIKE '%" + search + "%'");
				while(rs.next()){
					result.add(new Board(rs.getInt("id"), rs.getString("title"), rs.getString("date"), rs.getString("content"), rs.getString("nickname"), rs.getInt("user"), rs.getInt("cnt"), rs.getString("board")));
				}
			}
		}catch(Exception e){
			System.out.println("search fail : " + e.getMessage());
		}

		return result;
	}
}
