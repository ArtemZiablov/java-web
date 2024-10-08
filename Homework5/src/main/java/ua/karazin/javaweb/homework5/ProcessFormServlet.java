package ua.karazin.javaweb.homework5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/processForm")
public class ProcessFormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        // Отримання масиву параметрів
        String[] selectedFruits = request.getParameterValues("fruits");

        if (selectedFruits != null) {
            System.out.println("Обрані фрукти: ");
            for (String fruit : selectedFruits) {
                System.out.println(fruit);
            }
        } else {
            System.out.println("Фруктів не выбрано.");
        }

        // Передача даних на JSP
        request.setAttribute("name", name);
        request.setAttribute("age", age);
        request.setAttribute("selectedFruits", selectedFruits);

        // Перенаправлення на JSP
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
