package com.prac.exam.servlet;

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
            SecSql sql = SecSql.from("SELECT * ");
            sql.append("FROM article");
            sql.append("ORDER BY id DESC");

            List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

            req.setAttribute("articleRows", articleRows);
            req.getRequestDispatcher("../article/list.jsp").forward(req,resp);



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
}

