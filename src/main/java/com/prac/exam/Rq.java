package com.prac.exam;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    public Rq(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        this.req = req;
        this.resp = resp;

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset-utf-8");
    }

    public int getIntParam(String paramName, int defaultValue){
        String value = req.getParameter(paramName);

        if( value == null){
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public String getParam(String paramName, String defaultValue){
        String value = req.getParameter(paramName);

        if( value == null){
            return defaultValue;
        }
        return value;
    }

    public void appendBody(String str){
        try {
            resp.getWriter().append(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void jsp(String jspPath) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(jspPath + ".jsp");

        try {
            requestDispatcher.forward(req,resp);
        }
        catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }
}
