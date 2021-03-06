﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.bakbak2.web.*"%>
<%@ page import="com.bakbak2.model.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content=""width="device-width, initial-scale=1">

    <title>BBE</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
    <link rel="stylesheet" href="./css/bootstrap.css">
    <link rel="stylesheet" href="./css/common.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="./js/bootstrap.js"></script>
    <script type="text.javascript" src="./js/common.js"></script>

    <%
    session = request.getSession(true);
    %>
</head>
<body>
    <div class="jumbotron text-center mb-0">
        <h1>B B E</h1>
        <p>user custom board community</p>
    </div>

    <%
        List<String> board_list = (List<String>)session.getAttribute("board_list");
	Board post = (Board)request.getAttribute("post");
    %>

    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container-fluid">
            <a href="index.jsp" class="navbar-brand">BBE</a>

            <div class="navbar-nav navbar-left">
                <div class="nav-item">
                    <a href="board?bo=notice" class="nav-link nav_item_hover">공지사항</a>
                </div>
                <div class="nav-item">
                    <a href="request_board.jsp" class="nav-link nav_item_hover">게시판 생성</a>
                </div>
                <div class="btn-group">
                    <a href="#" class="nav-link nav_item_hover dropdown-toggle" data-toggle="dropdown">
                    게시판 목록
                    </a>
                    <ul class="dropdown-menu">
                        <%
                            for(int i = 0; i < board_list.size(); ++i) {
                        %>
                            <li><a href="board?bo=<%=board_list.get(i)%>"><%=board_list.get(i)%></a></li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
            <form class="navbar-form navbar-right" method="get" action="search.do">
                <div class="input-group">
                    <div class="input-group-append">
                        <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="통합검색" name="search"/>
                        <input type="submit" class="btn btn-default" value="검색"/>
                    </div>
                </div>
            </form>
        </div>
    </nav>

    <div class="row">
        <div class="col-xs-9">
            <div class="container">
                <div class="row">
                    <div class="col-xs-10">
                        <div class="table table-responsive">
                            <table class="table table-bordered">
                                <tr>
                                    <th class="active" width="80px">번호</th>
                                    <td><%=post.getId()%></td>
                                    <th class="active" width="80px">조회수</th>
                                    <td><%=post.getCnt()%></td>
                                </tr>
                                <tr>
                                    <th class="active" width="80px">작성자</th>
                                    <td><%=post.getNick()%></td>
                                    <th class="active" width="80px">작성일</th>
                                    <td><%=post.getDate()%></td>
                                </tr>
                                <tr>
                                    <th class="active">제목</th>
                                    <td colspan="3"><%=post.getTitle()%></td>
                                </tr>
                                <tr>
                                    <th class="active">내용</th>
                                    <td colspan="3"><%=post.getContent()%></td>
                                </tr>
                            </table>
                        </div>
                        <a class="btn btn-default pull-right" href="delete.do?bo=<%=post.getBoard()%>&id=<%=post.getId()%>">삭제</a>
                        <a class="btn btn-default pull-right" href="board?bo=<%=post.getBoard()%>">돌아가기</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-3">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="login-box well">
			<%if(session.getAttribute("isLogined")!=null && (Boolean)session.getAttribute("isLogined")){%>
				<%=session.getAttribute("NICK")%>님 환영합니다!
                            <hr/>
                            <form method="post" action="login.do">
                           	<input type="submit" class="btn btn-default btn-block" value="로그아웃"/>
		  </form>
			<%}else{%>
                            <form method="post" action="login.do">
                                <legend>로그인</legend>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                    <input type="text" class="form-control" placeholder="아이디" name="id"/>
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                    <input type="password" class="form-control" placeholder="비밀번호" name="pw"/>
                                </div>
                                <input type="submit" class="btn btn-default btn-block" value="로그인">
                                <span class="text-center"><a href="#" class="text-sm">비밀번호 찾기</a></span>
                                <div class="form-group">
                                    <a href="register.jsp" class="btn btn-default btn-block">회원가입</a>
                                </div>
                            </form>
			<%}%>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <hr/>
    <div class="row">
        <div class="col-xs-9">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="write-box">
                            <form class="form-group">
                                <textarea rows="3" class="form-control no_resize col-12" placeholder="댓글 작성" id="reply_content_text"></textarea><br/>
                                <input type="button" class="btn btn-default pull-right" value="작성"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="jumbotron text-center mt-5 mb-0">
        <h3 class="text-secondary">B B E</h3>
        <p>Copyright ⓒ 2019 박박이 All Right Reserved.</p>
    </div>
</body>
</html>