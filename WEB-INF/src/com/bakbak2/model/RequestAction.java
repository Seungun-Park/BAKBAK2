
package com.bakbak2.model;

import com.bakbak2.model.SQLAction;
import java.sql.ResultSet;

public class RequestAction{
	private String title = null;
	private String main_cat = null;
	private String sub_cat = null;
	private String reason = null;
	private String comment = null;

	public RequestAction(String title, String main_cat, String sub_cat, String reason, String comment){
		this.title = title;
		this.main_cat = main_cat;
		this.sub_cat = sub_cat;
		this.reason = reason;
		this.comment = comment;
	}

	public boolean tryRegister(){
		SQLAction sql = new SQLAction();
		ResultSet rs = null;

		rs = sql.execute("select * from board_list order by id");

		try{
			while(rs.next()){
				if(title.equals(rs.getString("name"))){
					return false;
				}
			}
			return sql.regBoard(title, main_cat, sub_cat, reason, comment);
		}catch(Exception e){
			System.out.println("DB fail to request board : " + e.getMessage());
		}

		return false;
	}
}
