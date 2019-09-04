<%@ page import="com.worm.TheImageController" %>
<%@ page import="java.util.List" %>
<%@ page import="com.worm.TheImage" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hey</title>
</head>
<body>

    Your image has been uploaded successfully
    <br/>Want to upload more? <a href="/showForm" >click</a>
    <br/>Want to see your album? <form action="album"><input type="submit" value="My Album"></form>



</body>
</html>
