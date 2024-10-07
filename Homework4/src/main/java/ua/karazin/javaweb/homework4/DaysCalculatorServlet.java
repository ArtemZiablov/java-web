package ua.karazin.javaweb.homework4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@WebServlet(name = "DaysCalculatorServlet", value = "/calculate-days")
public class DaysCalculatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String birthdateStr = request.getParameter("birthdate");
        String resultMessage;

        try {
            LocalDate birthdate = LocalDate.parse(birthdateStr);
            LocalDate currentDate = LocalDate.now();

            // Підрахунок кількості днів між датою народження та сьогоднішньою датою
            long daysLived = ChronoUnit.DAYS.between(birthdate, currentDate);
            resultMessage = "Ви прожили " + daysLived + " днів.";
        } catch (Exception e) {
            resultMessage = "Невірний формат дати. Спробуйте ще раз.";
        }

        // Передаємо результат у JSP сторінку
        request.setAttribute("result", resultMessage);
        request.getRequestDispatcher("days-calculator.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Якщо викликається GET-запит, просто перенаправляємо на JSP сторінку з формою
        request.getRequestDispatcher("days-calculator.jsp").forward(request, response);
    }
}
