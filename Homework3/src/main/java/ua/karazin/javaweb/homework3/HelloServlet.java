package ua.karazin.javaweb.homework3;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Выводим HTML с приветственным сообщением и ссылками на другие сервлеты
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");

        // Добавляем ссылки для перехода на session-servlet и cookie-servlet
        out.println("<p><a href='/session-servlet'>Перейти на Session Servlet</a></p>");
        out.println("<p><a href='/cookie-servlet'>Перейти на Cookie Servlet</a></p>");

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
