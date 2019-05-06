<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/15 0015
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.wind.entity.Book" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>list</title>
</head>
<body>
<%
    List<Book> books = (List) request.getAttribute("list");
    for (Book book:books){
        out.print(book.getName()+"<br />");
    }
%>
</body>
</html>
