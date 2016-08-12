<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <meta http-equiv="Content-Type" content="text/html;">
    <title>Help</title>
    <link href="${ctx}/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="page-header">
    <h1>Help</h1>
</div>
<h3>Environment setup and registration</h3>
<ol>
    <li>Download <a href="${ctx}/resources/user/client.zip">client</a></li>
    <li>Setup project according to instruction in README.txt for your developing language</li>
    <li>Read <a href="${ctx}/resources/help/help.html">game instructions</a>.</li>
    <li>Open <a href="${ctx}/register">registration page</a></li>
    <li>Enter your name/password and codenjoy!</li>
</ol>
</body>
</html>