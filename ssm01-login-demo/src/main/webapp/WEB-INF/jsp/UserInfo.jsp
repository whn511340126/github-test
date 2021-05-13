<%--
  Created by IntelliJ IDEA.
  User: Administrator
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserInfo.jsp</title>
</head>
<body>
    <h2>有效登录的用户信息如下:<a href="logout">登出</a></h2>
    ${sessionScope.validUser.id}&nbsp;&nbsp;
    ${sessionScope.validUser.email}&nbsp;&nbsp;
    ${sessionScope.validUser.nickname}&nbsp;&nbsp;
    ${sessionScope.validUser.password}&nbsp;&nbsp;
    ${sessionScope.validUser.userIntegral}&nbsp;&nbsp;
    ${sessionScope.validUser.isEmailVerify}&nbsp;&nbsp;
    ${sessionScope.validUser.emailVerifyCode}&nbsp;&nbsp;
    ${sessionScope.validUser.lastLoginTime}&nbsp;&nbsp;
    ${sessionScope.validUser.lastLoginIp}&nbsp;&nbsp;

</body>
</html>
