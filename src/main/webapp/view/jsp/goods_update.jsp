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
		<h1 class="mb-3" style="text-align: center">商品編集画面</h1>
		<form action="./GoodsUpdateServlet" method="post">
			<input type="hidden" name="goods_id" value="${requestScope.goods_id}">
 		  <div class="mb-3">
		    <label for="name" class="form-label">商品名</label>
		    <input type="text" class="form-control" id="update_goods_name" name="update_goods_name" value="${requestScope.goodsUpdate.update_goods_name}"required> 
		  </div>
		  <div class="mb-3">
		    <label for="address" class="form-label">単価</label>
		    <input type="text" class="form-control" id="update_goods_price" name="update_goods_price" value="${requestScope.goodsUpdate.update_goods_price}" required>
		  </div>
		  <div class="mb-3">
		    <label for="address" class="form-label">原価</label>
		    <input type="text" class="form-control" id="update_goods_cost" name="update_goods_cost" value="${requestScope.goodsUpdate.update_goods_cost}" required>
		  </div><br>
		  <button type="submit" class="btn btn-primary">更新</button>
		</form><br>
		<a href="./view/jsp/goods_menu.jsp" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">商品画面へ</a>
	</div>
</body>
</html>