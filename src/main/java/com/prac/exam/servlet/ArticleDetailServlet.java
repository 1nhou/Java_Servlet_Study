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

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
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
            //req.getParameter() String 형태로 받아오기때문에 형변환해서 사용할것.
            int id = Integer.parseInt(req.getParameter("id"));

            //String.format()을 이용하면 %d 서식 지정자 사용가능
//            String sql = String.format("SELECT * FROM article WHERE id = %d", id);

            SecSql sql = SecSql.from("SELECT * ");
            sql.append("FROM article");
            sql.append("WHERE id = ?", id);

            Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);


            req.setAttribute("articleRows", articleRow);
            rq.jsp("../article/detail");
//            req.getRequestDispatcher("../article/detail.jsp").forward(req,resp);
            //rq를 통해 메소드를 만들었기때문에 굳이 이렇게 처리하지않고 rq에게 토스



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

