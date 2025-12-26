<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="view/css/stylesheet.css">
<title>商品編集</title>
</head>
<body>
	<div class="mx-auto" style="text-align: center;">
		<h1 class="mb-3" style="text-align: center">顧客編集画面</h1>
		<form action="./CustomerUpdateServlet" method="post">
			<input type="hidden" name="customer_id" value="${requestScope.customer_id}">
 		  <div class="mb-3">
		    <label for="name" class="form-label">顧客名</label>
		    <input type="text" class="form-control" id="update_customer_name" name="update_customer_name" value="${requestScope.customerUpdate.update_customer_name}"required> 
		  </div><br>

		  <button type="submit" class="btn btn-primary">更新</button>
		</form><br>
		<a href="./view/jsp/customer_menu.jsp" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">顧客画面へ</a>
	</div>
</body>
</html>