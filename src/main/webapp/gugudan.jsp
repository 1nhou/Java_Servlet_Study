<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.prac.exam.Rq" %>
<%
    Rq rq = new Rq(request, response);
    int dan = rq.getIntParam("dan", 9);
    int limit = rq.getIntParam("limit", 9);
%>

<!-- 방식 1 -->
<!--<% out.println("Hi"); %>-->

<!-- 방식 2-->
<!--<h2><%="HI"%></h2>-->

<h1><%=dan%>단 테스트</h1>
<% for(int i = 1; i <= limit; i++) { %>
<div><%=dan%> * <%=i%> = <%=dan * i%></div>
<% } %>