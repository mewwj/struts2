<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="LoginAction.action" method="post">
         <h2 >欢迎注册</h2><br>
        输入账号（文本框）：<input type="text" name="name"><br>
        输入密码（密码框）：<input type="password" name="password"><br>
        选择性别（单选按钮）：
        <input type="radio" name="sex" checked="checked" >男
        <input type="radio" name="sex"  >女<br>
        选择爱好（复选框）：
         <input type="checkbox" name="hobby[0]" >唱歌
         <input type="checkbox" name="hobby[1]" >跳舞
         <input type="checkbox" name="hobby[2]" >打球
         <input type="checkbox" name="hobby[3]" >打游戏<br>
         <input type="submit" value="登录"  />
       </form>
</body>
</html>