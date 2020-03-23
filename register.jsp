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

    <div class="container col-md-offset-3">
        <div class="row">
            <div class="col-xs-10">
                <div class="register-box well">
                    <form method="post" action="register.do">
                        <legend>회원가입</legend>
                        <div class="form-group row">
                            <label class="col-form-label col-3">아이디 입력 : </label>
                            <div class="col-6">
                                <input type="text" class="form-control" placeholder="아이디" name="id" id="id_text"/>
                                <span id="overlapErr" class="help-block">사용할 수 없는 아이디 입니다.</span>
                            </div>
                            <div class="col-2">
                                <a class="btn btn-primary" id="check_id_overlap_btn">중복확인</a>
                            </div>
                            <div class="col-md-offset-3">
                                <label class="col-form-label col-12">
                                    5~20자 영문 또는 숫자로만 입력하시오.
                                </label>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-form-label col-3">비밀번호 입력 : </label>
                            <div class="col-6">
                                <input type="password" class="form-control" placeholder="비밀번호" name="pw" id="pw_text"/>
                                <span id="pwRegErr" class="help-block">사용할 수 없는 비밀번호 입니다.</span>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-form-label col-3">비밀번호 재 입력 : </label>
                            <div class="col-6">
                                <input type="password" class="form-control" placeholder="비밀번호 확인" name="pw_confirm" id="pw_text_confirm"/>
                                <span id="rePwRegErr" class="help-block">비밀번호와 일치하지 않습니다. 다시 입력하세요.</span>
                            </div>
                            <div class="col-md-offset-3">
                                <label class="col-form-label col-12">
                                    6~20자 영문 또는 숫자로만 입력하시오.
                                </label>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-form-label col-3">닉네임 입력 : </label>
                            <div class="col-6">
                                <input type="text" class="form-control" placeholder="닉네임" name="nickname" id="nickname_text"/>
                                <span id="nicknameRegErr" class="help-block">사용할 수 없는 닉네임입니다.</span>
                            </div>
                            <div class="col-2">
                                <a class="btn btn-primary" id="check_nickname_overlap_btn">중복확인</a>
                            </div>
                            <div class="col-md-offset-3">
                                <label class="col-form-label col-12">
                                    2~10자 한글,영문 또는 숫자로만 입력하시오.
                                </label>
                            </div>
                        </div>
                        <div class="col-5 center-block">
                            <input type="submit" class="btn btn-primary btn-block" value="회원가입" id="register_btn">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="jumbotron text-center mt-5 mb-0">
        <h3 class="text-secondary">B B E</h3>
        <p>Copyright ⓒ 2019 박박이 All Right Reserved.</p>
    </div>

    <script>
        var id_state = false;
        var pw_state = false;
        var rePw_state = false;
        var nick_state = false;

        $("#register_btn").attr("disabled", "disabled");

        $("#id_text").keyup(function(){
            var id=$(this).val();

            var reg=/([A-Z]|[a-z]|[0-9]){5,20}/;
            var reg2=/(\W|_)+/;
            if(reg.test(id) && !reg2.test(id) && (id.length >= 5 && id.length <= 20)) {
                id_state = true;
                $("#overlapErr").hide();
                successState("#id");
            } else {
                id_state = false;
                $("#overlapErr").show();
                errorState("#id");
            }
        });

        $("#pw_text").keyup(function(){
            var pwd=$(this).val();

            var reg=/([A-Z]|[a-z]|[0-9]){6,20}/;
            var reg2=/(\W|_)+/;
            if(reg.test(pwd) && !reg2.test(pwd) && (pwd.length >=6 && pwd.length <= 20)){
                pw_state = true;
                $("#pwRegErr").hide();
                successState("#pw_text");
            }else{
                pw_state = false;
                $("#pwRegErr").show();
                errorState("#pw_text");
            }
        });

        $("#pw_text_confirm").keyup(function(){
            var rePw=$(this).val();
            var pw=$("#pw_text").val();

            if((rePw == pw) && (rePw.length == pw.length)) {
                rePw_state = true;
                $("#rePwRegErr").hide();
                successState("#pw_text_confirm");
            } else {
                rePw_state = false;
                $("#rePwRegErr").show();
                errorState("#pw_text_confirm");
            }
        });

        $("#nickname_text").keyup(function(){
            var nickname=$(this).val();

            var reg1=/([가-힣]|[A-Z]|[a-z]|[0-9]){2,10}/;
            var reg2=/(\W|_)+/;
            if(reg1.test(nickname) && !reg2.test(nickname) && (nickname.length >= 2 && nickname.length <= 10)) {
                nick_state = true;
                $("#nicknameRegErr").hide();
                successState("#nickname_text");
            } else {
                nick_state = false;
                $("#nicknameRegErr").show();
                errorState("#nickname_text");
            }
        });

        function successState(sel){
            $(sel)
                .parent()
                .removeClass("has-error")
                .addClass("has-success")
                .find(".glyphicon")
                .removeClass("glyphicon-remove")
                .addClass("glyphicon-ok")
                .show();
            if(id_state && pw_state && rePw_state && nick_state) {
                $("#register_btn")
                    .removeAttr("disabled");
            }
        };

        function errorState(sel){
            $(sel)
                .parent()
                .removeClass("has-success")
                .addClass("has-error")
                .find(".glyphicon")
                .removeClass("glyphicon-ok")
                .addClass("glyphicon-remove")
                .show();

            if(!id_state || !pw_state || !rePw_state || !nick_state) {
                $("#register_btn")
                    .attr("disabled", "disabled");
            }
        };
    </script>
</body>
</html>