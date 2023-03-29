<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRows");
%>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시물 상세페이지</title>
</head>
<body>
<h1>게시물 상세페이지</h1>
<table border="1" style="text-align: center;">
    <thead>
    <colgroup>
        <col width="100">
        <col>
        <col>
        <col width="100">
        <col width="100">
        <col width="100">
    </colgroup>
        <tr>
            <th>번호</th>
            <th>현재 날짜</th>
            <th>수정 날짜</th>
            <th>제목</th>
            <th>내용</th>
            <th>비고</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><%= (int) articleRow.get("id")%></td>
            <td><%= (String) articleRow.get("regDate")%></td>
            <td><%= (String) articleRow.get("updateDate")%></td>
            <td><%= (String) articleRow.get("title")%></td>
            <td><%= (String) articleRow.get("body")%></td>
            <td>
                <a href="doDelete?id=<%= articleRow.get("id")%>"> 삭제하기</a>
            </td>
        </tr>
    </tbody>


</table>

<!--<div>번호 : <%= (int) articleRow.get("id")%></div>-->
<!--<div>현재 날짜 : <%= (String) articleRow.get("regDate")%></div>-->
<!--<div>수정 날짜 : <%= (String) articleRow.get("updateDate")%></div>-->
<!--<div>제목 : <%= (String) articleRow.get("title")%></div>-->
<!--<div>내용 : <%= (String) articleRow.get("body")%></div>-->
<div>
    <a href="list">리스트로 돌아가기</a>
</div>
</body>
</html>