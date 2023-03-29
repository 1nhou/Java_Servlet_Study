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

@WebServlet("/article/doDelete")
public class ArticleDoDeleteServlet extends HttpServlet {
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
//            int id = Integer.parseInt(req.getParameter("id"));

            //rq를 가져왔기때문에 더 쉬운 방법으로 불러오기
            int id = rq.getIntParam("id",0);

            //String.format()을 이용하면 %d 서식 지정자 사용가능
//            String sql = String.format("SELECT * FROM article WHERE id = %d", id);

            SecSql sql = SecSql.from("DELETE");
            sql.append("FROM article");
            sql.append("WHERE id = ?", id);

            DBUtil.delete(conn,sql);
            rq.appendBody(String.format("<script>alert('%d번글 삭제 완료.'); location.replace('list')</script>",id));



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

