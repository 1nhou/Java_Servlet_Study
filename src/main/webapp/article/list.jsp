<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows =(List<Map<String, Object>>) request.getAttribute("articleRows");
int cPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
%>

<html lang="ko">
<body>
<h1>게시물 리스트 v5</h1>
<table border="1" style="text-align: center;">
    <thead>
    <colgroup>
        <col width="100">
        <col>
        <col width="100">
    </colgroup>
    <tr>
        <th>번호</th>
        <th>현재 날짜</th>
        <th>제목</th>
    </tr>
    </thead>
    <tbody>
    <%
    for (Map<String, Object> articleRow : articleRows){
    %>
    <tr>
        <td><%= (int) articleRow.get("id")%></td>
        <td><%= (String) articleRow.get("regDate")%></td>
        <td>
            <a href="detail?id=<%= (int) articleRow.get("id")%>">
            <%= (String) articleRow.get("title")%>
            </a>
        </td>
    </tr>
    <%
    }
    %>
    </tbody>
</table>
<style type="text/css">
        .page > a.red {
        color : red;
        }
    </style>
<div class="page">
    <% for (int i = 1; i<=totalPage; i++) { %>
    <a class="<%= cPage == i ? "red" : ""  %>" href="list?page=<%=i%>"><%=i%></a>
    <% } %>
</div>

</body>
<head>
    <meta charset="UTF-8">
    <title>게시물 리스트</title>
</head>
</html>