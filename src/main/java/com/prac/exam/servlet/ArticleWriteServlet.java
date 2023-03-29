package com.prac.exam.servlet;

import com.prac.exam.Rq;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/article/write")
public class ArticleWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rq rq = new Rq(req,resp);
        // 한글 적용방법
//        req.setCharacterEncoding("UTF-8"); // 들어오는데이터 UTF-8로 해석한다
//        resp.setCharacterEncoding("UTF-8"); // 완성되는 HTML의 인코딩을 UTF-8로 하겠다
//        resp.setContentType("text/html; charset-utf-8"); // 브라우저에게 우리가 만든 결과물을 UTF-8이라고 알리는 의미

//        req.getRequestDispatcher("../home/main.jsp").forward(req,resp);

        rq.jsp("../article/write");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
