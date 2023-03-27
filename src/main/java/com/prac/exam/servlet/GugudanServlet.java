package com.prac.exam.servlet;

import com.prac.exam.Rq;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Rq rq = new Rq(req, resp);

        int dan = rq.getIntParam("dan", 0);
        int limit = rq.getIntParam("limit", 0);

        rq.appendBody(String.format("<div class=\"a\">%d단 test</div>\n", (dan)));
        for (int j = 1; j < limit; j++) {
            resp.getWriter().append(String.format("<div>%d * %d = %d</div>", (dan), (j), (dan * j)));
        }
    }
}
