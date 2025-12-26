<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="view/css/stylesheet.css">
<title>顧客情報登録</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1>顧客情報登録</h1>
<div class = "loginUser">
  <p>
   ログインユーザー： ${sessionScope.user.name }
  </p>
</div>

<main>
 <div class="container">
 
 <!-- 登録フォーム -->
   <form action="./CustomerRegisterServlet" method="post">
     <div class="form-group">
                    <label for="customer_id">顧客ID（4桁）</label><br>
                    <input type="text" name="customer_id" class="form-control" required>
     </div>
     <div class="form-group">
                    <label for="customer_name">顧客名</label><br>
                    <input type="text" name="customer_name" class="form-control" required>
     </div>
    
     <div class="form-group">
     <input type="submit" value="登録"> 
     </div>
    </form>
    
     <!-- 検索フォーム -->
   <form action="./CustomerSearchServlet" method="post">
     <div class="form-group">
                    <label for="customer_id">ID検索</label><br>
                    <input type="text" name="search_customer_id" class="form-control">
     </div>
     <div class="form-group">
                    <label for="customer_name">顧客名検索</label><br>
                    <input type="text" name="search_customer_name" class="form-control">
     </div>
     <div class="form-group">
     <input type="submit" value="検索"> 
     </div>
    </form>
  
     <!-- テーブル -->
  <table border="1" >
   <thead>
    <tr>
      <th width="6%">操作</th>
      <th width="15%">顧客ID</th>
      <th width="30%">顧客名</th>
    </tr>
   </thead>
   <tbody>
     <c:forEach var="customer" items="${sessionScope.customer}">
			 <tr>
			     <!-- JSTLを使用して商品IDのデータをリンクの -->
				<!-- パラメーターに設定してサーブレットで取得する -->
				
				<!-- 削除 -->
			    <c:url var="delete" value="/CustomerDeleteServlet">
			 	 <c:param name="customer_id" value="${customer.getCustomer_id()}"/>
			 	</c:url>
			 	
			 	<!-- 編集 -->
			 	<c:url var="update" value="/CustomerUpdateServlet">
			 		<c:param name="customer_id" value="${customer.getCustomer_id()}"/>
			 	</c:url>
			 	
			    <td><a href="${delete}" onclick="return Delete_Dialog()">削除</a> | <a href="${update}">編集</a></td>
			 	<td><c:out value="${customer.getCustomer_id()}" /></td>
			 	<td><c:out value="${customer.getCustomer_name()}" /></td>
			 	
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
		var res = confirm("選択した顧客データを削除します。よろしいですか?");
		if(res){
			return true;
		} else {
			return false;
		};
	};
</script>

</html>