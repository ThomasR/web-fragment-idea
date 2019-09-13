package com.example;

import java.io.IOException;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

public class MyServlet extends DefaultServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String resourcePath = request.getPathInfo();
        if (resourcePath != null && resourcePath.contains("/static")) {
            HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(request) {
                @Override
                public String getPathInfo() {
                    return "/static.html";
                }
            };
            super.serveResource(wrappedRequest, response, true, fileEncoding);
            return;
        }

        response.setContentType("text/html");

        Writer writer = response.getWriter();
        writer.write("<p>Hello World!</p>");
        writer.write("<a href=\"static\">Static resource</a>");
    }
}
