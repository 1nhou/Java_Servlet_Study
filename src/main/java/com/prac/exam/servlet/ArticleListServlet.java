package com.prac.exam.servlet;

import com.prac.exam.Rq;
import com.prac.exam.util.DBUtil;
import com.prac.exam.util.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rq rq = new Rq(req,resp);

        // DB 연결시작
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.printf("[ClassNotFoundException 예외, %s]", e.getMessage());
            System.out.println("DB 드라이버 클래스 로딩 실패");
            return;
        }

        String url = "jdbc:mysql://127.0.0.1:3306/JSP_Community?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
        String user = "root";
        String password = "P@ssw0rd";

        try {
            conn = DriverManager.getConnection(url, user, password);
//            DBUtil dbUtil = new DBUtil(req, resp);
//            정적 메서드화를 이용해 객체를 다시 만들지 않는다.

//            String sql = "SELECT * FROM article";
            int page = 1;

            if(req.getParameter("page") != null && req.getParameter("page").length() != 0){
//                page = Integer.parseInt(req.getParameter("page"));
                //rq 객체를 가져옴으로써 간단하게 서술
                page = rq.getIntParam("page", 0);
            }


            int itemInPage = 10;
            int limitFrom = (page -1) * itemInPage;

            SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
            sql.append("FROM article");


            int totalCount = DBUtil.selectRowIntValue(conn, sql);
            int totalPage = (int)Math.ceil((double)totalCount / itemInPage);



            sql = SecSql.from("SELECT * ");
            sql.append("FROM article");
            sql.append("ORDER BY id DESC");
            sql.append("LIMIT ?, ?", limitFrom,itemInPage);

            List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

            req.setAttribute("articleRows", articleRows);
            req.setAttribute("page",page);
            req.setAttribute("totalPage",totalPage);
            rq.jsp("../article/list");



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // DB 연결 끝
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

