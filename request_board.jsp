<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
    if(session.getAttribute("isLogined")!=null && (Boolean)session.getAttribute("isLogined")){}
    else{
        if(session.getAttribute("url")!=null) response.sendRedirect((String)session.getAttribute("url"));
        else response.sendRedirect("board");
    }
    if(session.getAttribute("bo")==null) response.sendRedirect("board");
    %>
</head>
<body>
    <div class="jumbotron text-center mb-0">
        <h1>B B E</h1>
        <p>user custom board community</p>
    </div>

    <%
        List<String> board_list = (List<String>)session.getAttribute("board_list");
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
                        <div class="request-box well">
                            <form method="post" action="request.do">
                                <legend>게시판 개설</legend>
                                <div class="form-group row">
                                    <label class="col-form-label col-3">
                                        게시판 이름 :
                                    </label>
                                    <div class="col-8">
                                        <input type="text" class="form-control" placeholder="이름 입력" name="title" id="title_text"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-3">
                                        게시판 메인 카테고리 :
                                    </label>
                                    <div class="col-8">
                                        <div class="btn-group">
                                            <input type="text" class="btn btn-default" placeholder="게시판 메인 카테고리 선택" id="maincat_text" readonly/>
                                            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a href="#" class="cat_game">게임</a></li>
                                                <li><a href="#" class="cat_cartoon">만화</a></li>
                                                <li><a href="#" class="cat_computer">컴퓨터</a></li>
                                                <li><a href="#" class="cat_school">학교</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-3">
                                        게시판 세부 카테고리 :
                                    </label>
                                    <div class="col-8">
                                        <input type="text" class="form-control" placeholder="세부 카테고리 입력" name="sub_cat" id="subcat_text"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-3">
                                        게시판 개설 목적 :
                                    </label>
                                    <div class="col-8">
                                        <textarea class="form-control no_resize" rows="4" placeholder="개시판 개설 목적 입력" name="request_reason" id="request_reason_text"></textarea>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-form-label col-3">
                                        게시판 한 줄 소개 :
                                    </label>
                                    <div class="col-8">
                                        <input type="text" class="form-control" placeholder="한 줄 소개 입력" name="board_introduce" id="introduce_board_text"/>
                                    </div>
                                </div>
                                <hr/>
                                <input type="submit" class="btn btn-default pull-right" value="개설하기" id="request_btn">
                            </form>
                        </div>
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

    <div class="jumbotron text-center mt-5 mb-0">
        <h3 class="text-secondary">B B E</h3>
        <p>Copyright ⓒ 2019 박박이 All Right Reserved.</p>
    </div>
    <script>
        $(".cat_game").click(function() {
            $(this).val('game');
            $("#maincat_text").attr("placeholder", "게임");
        });

        $(".cat_cartoon").click(function() {
            $(this).val('cartoon');
            $("#maincat_text").attr("placeholder", "만화");
        });

        $(".cat_computer").click(function() {
            $(this).val('computer');
            $("#maincat_text").attr("placeholder", "컴퓨터");
        });

        $(".cat_school").click(function() {
            $(this).val('school');
            $("#maincat_text").attr("placeholder", "학교");
        });
    </script>
</body>
</html>