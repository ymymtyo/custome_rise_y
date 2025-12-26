<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="view/css/stylesheet.css">
<title>売上情報登録</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1>売上データ登録</h1>
<div class = "loginUser">
  <p>
   ログインユーザー： ${sessionScope.user.name }
  </p>
</div>

<main>
 <div class="container">
 
  <!-- 登録フォーム -->
  
   <form action="./ProceedsResisterServlet" method="post">
     <div class="form-group">
                    <label for="person">担当者</label><br>
                    <input type="text" name="person" class="form-control" required>
     </div>
     <div class="form-group">
                    <label for="sales_date">売上日</label><br>
                    <input type="date" name="sales_date" class="form-control" required>
     </div>
    
     <div class="form-group">
                    <label for="customer_name">顧客名</label><br>
                <select name="customer_id">
                  <c:forEach var="item" items="${customerList}">
                    <option value="${item.customer_id}">${item.customer_name}</option>
                  </c:forEach>
                </select>
     </div>
     
    <div class="form-group">
                    <label for="goods_name">商品名</label><br>
                   <select name="goods_id">
                     <c:forEach var="goodsitem" items="${goodsList}">
                      <option value="${goodsitem.goods_id}">${goodsitem.goods_name}</option>
                     </c:forEach>
                   </select>
         
     </div>
     <div class="form-group">
                    <label for="pcs">数量(1～99ヶ)</label><br>
                    <input type="text" name="pcs" class="form-control" required>
     </div>
     
     <div class="form-group">
     <input type="submit" value="登録"> 
     </div>
    </form>


    <!-- 検索フォーム -->
   <form action="./ProceedsRegisterSearchServlet.java" method="post">
     <div class="form-group">
                    <label for="search_person">担当者検索</label><br>
                    <input type="text" name="search_person" class="form-control">
     </div>
     <div class="form-group">
                    <label for="search_sales_date">売上日検索</label><br>
                    <input type="date" name="search_sales_date" class="form-control">
     </div>
      <div class="form-group">
                    <label for="search_goods_name">顧客名検索</label><br>
                    <input type="text" name="search_customer_name" class="form-control">
     </div> <div class="form-group">
                    <label for="search_customer_name">商品名検索</label><br>
                    <input type="text" name="search_goods_name" class="form-control">
     </div>
     
     <div class="form-group">
     <input type="submit" value="検索"> 
     </div>
    </form>
     

    <!-- テーブル -->
  <table border="1" >
   <thead>
    <tr>
      <th width="10%">操作</th>
      <th width="7%">担当者</th>
      <th width="12%">売上日</th>
      <th width="14%">顧客名</th>
      <th width="16%">商品名</th>
      <th width="8%">商品単価</th>
      <th width="8%">数量</th>
     <th width="8%">売上金</th>
      <th width="8%">原価</th>
       <th width="8%">利益</th>
    </tr>
   </thead>
   <tbody>
   
     <c:forEach var="customerRegister" items="${sessionScope.customerRegisterList}">
			 <tr>
			 	  <!-- JSTLを使用して商品IDのデータをリンクの -->
				<!-- パラメーターに設定してサーブレットで取得する -->
				
				<!-- 削除 -->
			    <c:url var="delete" value="/ProceedsDeleteServlet">
			 	 <c:param name="proceeds_id" value="${customerRegister.getProceeds_id()}"/>
			 	</c:url>
			 	
			 	<!-- 編集 -->
			 	<c:url var="update" value="/ProceedsUpdateServlet">
			 		<c:param name="proceeds_id" value="${customerRegister.getProceeds_id()}"/>
			 	</c:url>
			 	
			    <td><a href="${delete}" onclick="return Delete_Dialog()">削除</a> | <a href="${update}">編集</a></td>
			 	<td><c:out value="${customerRegister.getPerson()}" /></td>
			 	<td><c:out value="${customerRegister.getSales_date()}" /></td>
			 	<td><c:out value="${customerRegister.getCustomer_name()}" /></td>
			 	<td><c:out value="${customerRegister.getGoods_name()}" /></td>
			 	<td><c:out value="${customerRegister.getGoods_price()}" /></td>
			 	<td><c:out value="${customerRegister.getPcs()}" /></td>
			 	<td><c:out value="${customerRegister.getSales()}" /></td>
			 	<td><c:out value="${customerRegister.getGoods_cost()}" /></td>
			 	<td><c:out value="${customerRegister.getProfit()}" /></td>
			 	
			 </tr>
   	 </c:forEach>
   			 
   </tbody>
   
  </table><br>
</div>

<div class = "topBack">
<a href="./BackTopServlet">メニュー画面へ戻る</a>
</div>


</main>

<jsp:include page="logoutButton.jsp"></jsp:include>
</body>

<script type="text/javascript">
	function Delete_Dialog(){
		var res = confirm("選択した売上データを削除します。よろしいですか?");
		if(res){
			return true;
		} else {
			return false;
		};
	};
</script>

</html>