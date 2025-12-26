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
		<h1 class="mb-3" style="text-align: center">売上データ編集画面</h1>
		<form action="./ProceedsUpdateServlet" method="post">
		
			<input type="hidden" name="proceeds_id" value="${requestScope.proceeds_id}">
			
 		  <div class="mb-3">
		    <label for="name" class="form-label">担当者</label>
		    <input type="text" class="form-control" id="update_person" name="update_person" value="${requestScope.customerRegisterUpdate.update_person}"required> 
		  </div>
		  
		  <div class="mb-3">
		    <label for="address" class="form-label">売上日</label>
		    <input type="date" class="form-control" id="update_sales_date" name="update_sales_date" value="${requestScope.customerRegisterUpdate.update_sales_date}" required>
		  </div>
		  
		  <div class="mb-3">
		    <label for="address" class="form-label">顧客名</label>
		     <select name="update_customer_id">
		      <option value="${requestScope.customerRegisterUpdate.update_customer_id}" selected>${requestScope.customerRegisterUpdate.update_customer_name}</option>
                  <c:forEach var="item" items="${customerList}">
                 <option value="${item.customer_id}">${item.customer_name}</option>
                  </c:forEach>
                </select>
		  </div>
		  
		   <div class="mb-3">
		    <label for="address" class="form-label">商品名</label>
		   <select name="update_goods_id">
		             <option value="${requestScope.customerRegisterUpdate.update_goods_id}"selected>${requestScope.customerRegisterUpdate.update_goods_name}</option>
                     <c:forEach var="goodsitem" items="${goodsList}">
                     <option value="${goodsitem.goods_id}">${goodsitem.goods_name}</option>
                     </c:forEach>
                   </select>
		  </div>
		  
		   <div class="mb-3">
		    <label for="address" class="form-label">数量</label>
		    <input type="text" class="form-control" id="update_pcs" name="update_pcs" value="${requestScope.customerRegisterUpdate.update_pcs}" required>
		  </div><br>
		  
		  <button type="submit" class="btn btn-primary">更新</button>
		</form><br>
		<a href="./view/jsp/goods_menu.jsp" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">売上画面へ</a>
	</div>
</body>
</html>