/*박박이*/

package com.bakbak2.model;

import com.bakbak2.model.SQLAction;
import java.sql.ResultSet;

public class LoginAction{
	private String id = null;
	private String pw = null;
	private String nick = null;
	private int idx = 0;

	public LoginAction(String id, String pw){
		this.id = id;
		this.pw = pw;
	}

	public String getId(){
		return id;
	}

	public String getNick(){
		return nick;
	}

	public int getIdx(){
		return idx;
	}

	public boolean tryLogin(){
		SQLAction sql = new SQLAction();
		
		//user 테이블 읽어오기 질의
		ResultSet rs = sql.execute("select * from user_tbl order by id");

		//user 테이블이 비어있으면 실패
		if(rs == null) return false;
		
		String db_id = null;
		String db_pw = null;

		try{
			rs.beforeFirst();
			while(rs.next()){
				db_id = rs.getString("user_id");
				db_pw = rs.getString("user_pw");

				if(id.equals(db_id) && pw.equals(db_pw)){
					idx = rs.getInt("id");
					nick = rs.getString("nickname");
					return true;
				}
			}
		}catch(Exception e){
			System.out.println("DB login error : " + e.getMessage());
		}

		return false;
	}
}
