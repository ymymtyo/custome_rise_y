<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="view/css/stylesheet.css">
<title>会員メニュー</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<div class = "loginUser">
  <p>
   ログインユーザー： ${sessionScope.user.name }
  </p>
</div>

<h1>メニュー</h1>

<form action="./goodsMenuTopServlet" method ="get" style="text-align: center;">
<input class="topmenu" type="submit" value="商品情報登録"> 
</form>
<br>
<form action="./customerMenuTopServlet" method ="get" style="text-align: center;">
<input class="topmenu" type="submit" value="顧客情報登録"> 
</form>
<br>
<form action="./proceedsMenuTopServlet" method ="get" style="text-align: center;">
<input class="topmenu" type="submit" value="売上情報登録"> 
</form>
<br>

<jsp:include page="./logoutButton.jsp"></jsp:include>
</body>
</html>