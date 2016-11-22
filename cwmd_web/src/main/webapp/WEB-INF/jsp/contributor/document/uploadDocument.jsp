<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/imports.jsp" %>
<html>
<head>
    <title>Working Area</title>
</head>
<body>
<form method="POST" action="<c:url value='/document/upload'/>" enctype="multipart/form-data" >
    <label for="file">Choose file:</label>
    <input type="file" name="file" id="file"/>
    <br/>
    <input type="hidden" value="${documentId}" name="documentId" />
    <input type="submit" value="Upload" name="upload" id="upload" />
</form>

</body>
</html>
