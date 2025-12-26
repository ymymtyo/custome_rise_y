<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="view/css/stylesheet.css">
<title>商品情報登録</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1>商品情報登録</h1>
<div class = "loginUser">
  <p>
   ログインユーザー： ${sessionScope.user.name }
  </p>
</div>

<main>
 <div class="container">
 
 <!-- 登録フォーム -->
   <form action="./GoodsRegisterServlet" method="post">
     <div class="form-group">
                    <label for="goods_id">商品ID（4桁）</label><br>
                    <input type="text" name="goods_id" class="form-control" required>
     </div>
     <div class="form-group">
                    <label for="goods_name">商品名</label><br>
                    <input type="text" name="goods_name" class="form-control" required>
     </div>
     <div class="form-group">
                    <label for="goods_price">単価</label><br>
                    <input type="text" name="goods_price" class="form-control" required>
     </div>
     <div class="form-group">
                    <label for="goods_cost">原価</label><br>
                    <input type="text" name="goods_cost" class="form-control" required>
                    
     </div>
     <div class="form-group">
     <input type="submit" value="登録"> 
     </div>
    </form>
  
   <!-- 検索フォーム -->
   <form action="./GoodsSearchServlet" method="post">
     <div class="form-group">
                    <label for="goods_id">ID検索</label><br>
                    <input type="text" name="search_goods_id" class="form-control">
     </div>
     <div class="form-group">
                    <label for="goods_name">商品名検索</label><br>
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
      <th width="15%">商品ID</th>
      <th width="30%">商品名</th>
      <th width="20%">単価</th>
      <th width="20%">原価</th>
    </tr>
   </thead>
   <tbody>
     <c:forEach var="goodsSearch" items="${sessionScope.goodsSearch}">
			 <tr>
			    <!-- JSTLを使用して商品IDのデータをリンクの -->
				<!-- パラメーターに設定してサーブレットで取得する -->
				<!-- 削除 -->
			    <c:url var="delete" value="/GoodsDeleteServlet">
			 	 <c:param name="goods_id" value="${goodsSearch.getSearch_goods_id()}"/>
			 	</c:url>
			 	
			 	<!-- 編集 -->
			 	<c:url var="update" value="/GoodsUpdateServlet">
			 		<c:param name="goods_id" value="${goodsSearch.getSearch_goods_id()}"/>
			 	</c:url>
	 	
			 	<td><a href="${delete}" onclick="return Delete_Dialog()">削除</a> | <a href="${update}">編集</a></td>
			 	<td><c:out value="${goodsSearch.getSearch_goods_id()}" /></td>
			 	<td><c:out value="${goodsSearch.getSearch_goods_name()}" /></td>
			 	<td><c:out value="${goodsSearch.getSearch_goods_price()}" /></td>
			 	<td><c:out value="${goodsSearch.getSearch_goods_cost()}" /></td>
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
		var res = confirm("選択した商品データを削除します。よろしいですか?");
		if(res){
			return true;
		} else {
			return false;
		};
	};
</script>

</html>