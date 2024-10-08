package ua.karazin.javaweb.homework6;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/testServlet") // URL для визову сервлета
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Передаємо данні в запрос
        String userName = "Артем";
        List<String> fruits = Arrays.asList("Яблуко", "Банан", "Апельсин");

        // Передаємо данні в request
        request.setAttribute("userName", userName);
        request.setAttribute("fruits", fruits);

        // Перенаправляємо запрос на test.jsp
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }
}