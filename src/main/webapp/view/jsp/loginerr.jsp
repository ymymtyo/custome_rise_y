<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="view/css/stylesheet.css">
<title>エラーページ</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<br>
<div class ="loginErr">
${requestScope.errMsg1}<br> <!-- ユーザーIDまたはパスワードが誤っています -->

<br>

<a href="./index">ログイン画面へ戻る</a>

</div>
</body>
</html>