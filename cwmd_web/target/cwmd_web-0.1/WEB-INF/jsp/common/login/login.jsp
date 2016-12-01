<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/imports.jsp" %>
<html>
<head>
    <title>Login</title>
    <base href="${pageContext.request.contextPath}">
    <link rel="stylesheet" type="text/css" href="\resources\css\style.css">
</head>
<body>
	<div class="container-fluid login-container">
	    <form:form id="login-form" method="post" commandName="user" class="login-form">
				<div class="row">
					<div class="registerBox">
						<div class="row">
							<div class="col-md-12">
								<label id="input-label">Username or Email*</label>
								<input  type="text">
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<label id="input-label" >Password*</label>
								<input type="password">
							</div>
						</div>
						
						<div class="row">
							<div class="text-center fullWidth">
								<button class="btn" type="submit" >Login</button>
							</div>
						</div>
					</div>
				</div>
		</form:form>
	</div>

</body>
</html>