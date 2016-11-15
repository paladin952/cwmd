<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/imports.jsp" %>
<html>
<head>
    <title>Login</title>
    <base href="${pageContext.request.contextPath}">
    <link rel="stylesheet" href="<c:url value='/resources/css/ui.css'/>"/>
</head>
<body>
<div class="container">
    <h1 class="welcome-msg">
        LOG IN
    </h1>
    <form:form id="login-form" method="post" commandName="user" class="login-form">
        <div id="error">
            ${error}
        </div>
        <div id="username-group">
            <span>
                Username:
            </span>
            <br />
            <form:input type="text" name="username" title="username" path="username"/>
            <form:errors path="username" cssClass="from-errors"/>
        </div>
        <br />
        <div id="password-group">
            <span>
                Password:
            </span>
            <br />
            <form:input type="password" name="password" title="password" path="password"/>
            <form:errors path="password" cssClass="from-errors"/>
        </div>
        <br />
        <button type="submit" id="btn-login" onclick="form.action='<c:url value='/login'/>'">LOG IN</button>
        <button type="submit" id="btn-register" onclick="form.action='<c:url value='/register'/>'">REGISTER</button>
    </form:form>
</div>
</body>
</html>
