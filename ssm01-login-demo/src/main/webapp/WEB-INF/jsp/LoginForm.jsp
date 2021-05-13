<%--
  Created by IntelliJ IDEA.
  User: Administrator
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>LoginForm.jsp</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <h2>登录表单</h2>
    <div class="redColor">
        ${errorMessage}
    </div>
    <form:form modelAttribute="user" action="login" method="post">
        <p>
            <div><form:errors path="email" cssClass="redColor"/> </div>
            用户邮箱:<form:input path="email"/>
        </p>
        <p>
            <div><form:errors path="password" cssClass="redColor"/> </div>
            用户密码:<form:input path="password"/>
        </p>
        <p>
            <input type="reset" value="重置"/>
            <input type="submit" value="提交"/>
        </p>
    </form:form>
</body>
</html>
