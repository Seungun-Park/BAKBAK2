/*박박이*/

package com.bakbak2.model;

import java.sql.*;
import java.io.*;

public class SQLAction{
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	public SQLAction(){
		try{
			File file = new File("C:\\SQLdata");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String url = bufReader.readLine();
			String id = bufReader.readLine();
			String pw = bufReader.readLine();

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,id,pw);
			stmt = conn.createStatement();
		}catch(Exception e){
			System.out.println("DB login fail : " + e.getMessage());
		}
	}

	public ResultSet execute(String sql){
		try{
			rs = stmt.executeQuery(sql);
		}catch(Exception e){
			System.out.println("DB query error : " + e.getMessage());
		}
		return rs;
	}	

	public boolean regUser(String id, String pw, String nick){
		try{
			pstmt = conn.prepareStatement("insert into user_tbl values(?,?,?,?)");
			rs.last();
			pstmt.setInt(1, rs.getInt("id")+1);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			pstmt.setString(4, nick);

			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("DB add user fail : " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean regBoard(String title, String main_cat, String sub_cat, String reason, String comment){
		try{
			pstmt = conn.prepareStatement("create table " + title + "(id int, title varchar(20), date varchar(19), content varchar(1000), user int, nickname varchar(20), cnt int, board varchar(20), primary key(id), foreign key(user) references user_tbl(id) on update cascade);");
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("insert into " + title + " values(0, '공지사항', '2019-12-15 08:56:00', '공지사항을 꼭 확인해 주세요.', 0, 'admin0', 0,'" + title +"')");
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("insert into board_list values(?,?,?,?,?,?)");
			rs.last();
			pstmt.setInt(1, rs.getInt("id")+1);
			pstmt.setString(2, title);
			pstmt.setString(3, main_cat);
			pstmt.setString(4, sub_cat);
			pstmt.setString(5, comment);
			pstmt.setString(6, reason);

			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("DB fail to add board : " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean insertPost(String board, String title, String date, String content, int user_idx, String nick){
		try{
			rs = stmt.executeQuery("select id from " + board + " order by id");
			rs.last();
			pstmt = conn.prepareStatement("insert into " + board + " values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, rs.getInt("id") + 1);
			pstmt.setString(2, title);
			pstmt.setString(3, date);
			pstmt.setString(4, content);
			pstmt.setInt(5, user_idx);
			pstmt.setString(6, nick);
			pstmt.setInt(7, 0);
			pstmt.setString(8, board);

			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("DB fail insert post : " + e.getMessage());
			return false;
		}
		return true;
	}

	public void cntUp(int id, String bo, int cnt){
		try{
			pstmt = conn.prepareStatement("update " + bo + " set cnt=" +cnt+" where id="+id);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("DB fail to update cnd : " + e.getMessage());
		}
	}

	public boolean doDel(String bo, int id){
		try{
			pstmt = conn.prepareStatement("delete from "+bo+" where id="+id);
			pstmt.executeUpdate();
			return true;
		}catch(Exception e){
			System.out.println("DB fail to delete : " + e.getMessage());
		}
		return false;
	}
}
