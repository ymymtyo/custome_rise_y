<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="view/css/stylesheet.css">
<title>登録完了</title>
</head>
<body>
<main>
 <h1>登録結果</h1>
  <div class = "loginUser">
   <p>
    ログインユーザー： ${sessionScope.user.name }
   </p>
  </div>

<div class="ok">
 ${requestScope.msg3}    <!-- 商品が登録できました -->
 ${requestScope.msg5}    <!-- 顧客が登録できませんでした -->
 ${requestScope.msg6}    <!-- 売上が登録できました -->
 ${requestScope.msg7}    <!-- 商品データを削除しました -->
 ${requestScope.msg8}    <!-- 顧客データを削除しました -->
 ${requestScope.msg9}    <!-- 売上データを削除しました -->
 ${requestScope.msg10}    <!-- 商品データを更新しました -->
 ${requestScope.msg11}    <!-- 顧客データを更新しました -->
 ${requestScope.msg12}    <!-- 売上データを更新しました -->
 </div>
 
 <div class ="ng">
 <p>
 ${requestScope.errMsg3} <!-- 商品が登録できませんでした -->
 ${requestScope.errMsg5} <!-- 顧客が登録できました -->
 ${requestScope.errMsg6}    <!-- 数量は1～99ヶで入力してください -->
 ${requestScope.errMsg7}    <!-- 顧客IDは4桁で入力してください -->
 ${requestScope.errMsg8}    <!-- 商品IDは4桁で入力してください -->

</p>
 </div>
 
<br><br>
 <div class = "topBack">
   <a href="./BackTopServlet">メニュー画面へ戻る</a><br><br>
   <a href="./goodsMenuTopServlet">商品情報画面へ戻る</a><br><br>
   <a href="./customerMenuTopServlet">顧客情報画面へ戻る</a><br><br>
   <a href="./proceedsMenuTopServlet">売上画面へ戻る</a><br>
  </div>
</main>

</body>
</html>