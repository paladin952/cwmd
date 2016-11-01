<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <base href="${pageContext.request.contextPath}">
    <link type="text/css" rel="stylesheet" href="/resources/css/ui.css" />
</head>
<body>
<div class="container">
    <h1 class="welcome-msg">
        LOG IN
    </h1>
    <form id="login-form" method="post">
        <div id="error">
            ${error}
        </div>
        <div id="username-group">
            <span>
                Username:
            </span>
            <br />
            <input type="text" name="username" title="username">
        </div>
        <br />
        <div id="password-group">
            <span>
                Password:
            </span>
            <br />
                <input type="password" name="password" title="password">
        </div>
        <br />
        <button type="submit" id="btn-login" onclick="form.action='${pageContext.request.contextPath}/login'">LOG IN</button>
        <button type="submit" id="btn-register" onclick="form.action='${pageContext.request.contextPath}/register'">REGISTER</button>
    </form>
</div>
</body>
</html>
