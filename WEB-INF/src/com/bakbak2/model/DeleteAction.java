package com.bakbak2.model;

import com.bakbak2.model.SQLAction;
import java.sql.ResultSet;

public class DeleteAction{
	SQLAction sql = null;
	ResultSet rs = null;

	public DeleteAction(){
		sql = new SQLAction();
	}

	public boolean doDel(String bo, int id, int user){
		rs = sql.execute("select user from "+bo+" where id =" +id);
		try{
			rs.next();
			int ref = rs.getInt("user");

			if(ref == user){
				return sql.doDel(bo, id);
			}
			else{
				rs = sql.execute("select * from admin_list");
				while(rs.next()){
					if(user == rs.getInt("ref")){
						return sql.doDel(bo, id);
					}
				}
			}
		}catch(Exception e){
			System.out.println("fail to delete action : " + e.getMessage());
		}
		return false;
	}
}
