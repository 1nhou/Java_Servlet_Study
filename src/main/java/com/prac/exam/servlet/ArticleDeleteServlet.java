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
import java.util.Map;

@WebServlet("/article/doDelete")
public class ArticleDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // 들어오는데이터 UTF-8로 해석한다
        resp.setCharacterEncoding("UTF-8"); // 완성되는 HTML의 인코딩을 UTF-8로 하겠다
        resp.setContentType("text/html; charset-utf-8"); // 브라우저에게 우리가 만든 결과물을 UTF-8이라고 알리는 의미

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

            SecSql sql = SecSql.from("DELETE * ");
            sql.append("FROM article");
            sql.append("WHERE id = ?", id);

            DBUtil.delete(conn,sql);
            resp.getWriter().append("<script>alert('3번글 삭제 완료.'); location.replace('list')</script>");



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

