<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="view/css/stylesheet.css">
<title>ログイン</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1 style="text-align: center;">ログイン</h1>

<form action="./userLogin" method="post" style="text-align: center;">
ユーザーID：<input type="text" name="userId"><br>
<br>
パスワード：<input type="password" name="userPass"><br>
<br>
<input type="submit" value="ログイン"> <br>

<p>(ログインID：000002　パスワード：7410)</p>

</form>
</body>
</html>