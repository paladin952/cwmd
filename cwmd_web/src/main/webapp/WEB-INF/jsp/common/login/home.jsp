<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/imports.jsp" %>
<html>
<head>
    <title>Home</title>
    <base href="${pageContext.request.contextPath}">
    <link rel="stylesheet" type="text/css" href="\resources\css\style.css">
</head>
<body>

		<div class="header">
		<label class="welcomeLabel" >Welcome!</label>
		</div>
		
		<table>
		<tr>
		<td>
			<div class="upload">
					<h2>Upload Document</h2>
					<button class="btn" type="submit">Upload</button>
			</div>
		</td>
		<td>
			<div class="download">
					<h2>Download Template</h2>
					<button class="btn" type="submit">Dowload</button>
			</div>
			</td>
			<tr>
		</table>

</body>
</html>
