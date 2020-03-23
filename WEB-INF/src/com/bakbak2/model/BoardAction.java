/*
 * 박박이
 * 게시판 목록을 읽어오는 액션
 * */

package com.bakbak2.model;

import com.bakbak2.model.SQLAction;
import java.util.*;
import java.sql.ResultSet;

public class BoardAction{
	SQLAction sql = null;

	public BoardAction(){
		sql = new SQLAction();
	}

	public List<String> loadBoardList(){
		ResultSet rs = null;
		List<String> board = new ArrayList<>();
		rs = sql.execute("select name from board_list order by id");
		try{
			rs.beforeFirst();
			while(rs.next()){
				board.add(rs.getString("name"));
			}
		}catch(Exception e){
			System.out.println("fail to load board list : " + e.getMessage());
		}
		return board;
	}
}
