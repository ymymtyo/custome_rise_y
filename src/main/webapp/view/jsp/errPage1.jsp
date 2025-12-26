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

${requestScope.errMsg2}<br> <!-- 商品データが取得できませんでした -->
${requestScope.errMsg4}<br> <!-- 顧客データが取得できませんでした -->
${requestScope.errMsg7}<br> <!-- 売上データが取得できませんでした -->
<br>


<a href="./BackTopServlet">メニュー画面へ戻る</a>

</div>
</body>
</html>