package ua.karazin.javaweb.homework4;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RootServlet", urlPatterns = "/")
public class RootServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Перенаправляємо на /calculate-days
        response.sendRedirect(request.getContextPath() + "/calculate-days");
    }
}

