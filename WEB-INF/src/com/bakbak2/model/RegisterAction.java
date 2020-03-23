/*박박이*/

package com.bakbak2.model;

import java.sql.ResultSet;
import com.bakbak2.model.SQLAction;

public class RegisterAction{
	private String id = null;
	private String pw = null;
	private String nick = null;

	public RegisterAction(String id, String pw, String nick){
		this.id = id;
		this.pw = pw;
		this.nick = nick;
	}

	public boolean tryRegister(){
		SQLAction sql = new SQLAction();
		ResultSet rs = null;

		rs = sql.execute("select * from user_tbl order by id");
		
		if(id.contains("admin")) return false;
		if(nick.contains("admin")) return false;
		if(nick.contains("관리자")) return false;
		if(id.length()<5 || id.length()>20) return false;
		if(pw.length()<6 || pw.length()>20) return false;
		if(nick.length()<2 || nick.length()>10) return false;

		try{
			while(rs.next()){
				if(id.equals(rs.getString("user_id")) || nick.equals(rs.getString("nickname"))){
					return false;
				}
			}
			return sql.regUser(id, pw, nick);
		}catch(Exception e){
			System.out.println("DB fail to register user: " + e.getMessage());
		}

		return false;
	}
}
