package com.bakbak2.model;

public class Board{
	int id, user, cnt;
	String nick, title, content, date, board;

	public Board(int id, String title, String date, String content, String nick, int user, int cnt, String board){
		this.id = id;
		this.title = title;
		this.date = date;
		this.content = content;
		this.nick = nick;
		this.user = user;
		this.cnt = cnt;
		this.board = board;
	}

	public String getBoard(){
		return board;
	}

	public int getId(){
		return id;
	}

	public String getDate(){
		return date;
	}

	public String getContent(){
		return content;
	}

	public String getTitle(){
		return title;
	}

	public String getNick(){
		return nick;
	}

	public int getUser(){
		return user;
	}

	public int getCnt(){
		return cnt;
	}
}
